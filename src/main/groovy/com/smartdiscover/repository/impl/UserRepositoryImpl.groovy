package com.smartdiscover.repository.impl

import com.smartdiscover.domain.User
import com.smartdiscover.repository.UserRepository
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
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

    /*@Override
    @Transactional
    void deleteById(@NotNull Long id) {
        findById(id).ifPresent { user -> entityManager.remove(user) }
    }

    final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name")

    @Transactional(readOnly = true)
    List<User> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = 'SELECT u FROM user as u'
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + " " + args.getOrder().get().toLowerCase()
        }
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class)
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration:: getMax))
        args.getOffset().ifPresent(query:: setFirstResult)

        return query.getResultList()
    }

    @Override
    @Transactional
    int update(@NotNull Long id, @NotBlank String name) {
        return entityManager.createQuery("UPDATE User g SET name = :name where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate()
    }*/
}
