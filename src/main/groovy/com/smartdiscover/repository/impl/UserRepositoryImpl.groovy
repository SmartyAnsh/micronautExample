package com.smartdiscover.repository.impl

import com.smartdiscover.domain.User
import com.smartdiscover.repository.UserRepository
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Singleton
class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager

    UserRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    @Transactional(readOnly = true)
    Optional<User> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id))
    }

    @Override
    @Transactional
    User save(@NotBlank String firstName, @NotBlank String lastName, @NotBlank int age) {
        User user = new User(firstName: firstName, lastName: lastName, age: age)
        entityManager.persist(user)
        return user
    }

    @Override
    @Transactional
    int update(@Valid User user) {
        return entityManager.createQuery("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.age = :age where u.id = :id")
                .setParameter("firstName", user.firstName)
                .setParameter("lastName", user.lastName)
                .setParameter("age", user.age)
                .setParameter("id", user.id)
                .executeUpdate()
    }

    @Override
    @Transactional
    void deleteById(@NotNull Long id) {
        findById(id).ifPresent { user -> entityManager.remove(user) }
    }

    @Transactional(readOnly = true)
    List<User> findAll() {
        String qlString = "SELECT u FROM User u"
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class)
        return query.getResultList()
    }

}
