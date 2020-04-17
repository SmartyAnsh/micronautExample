package com.smartdiscover.repository.impl

import com.smartdiscover.domain.Employee
import com.smartdiscover.repository.EmployeeRepository
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
class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager

    EmployeeRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    @Transactional(readOnly = true)
    Optional<Employee> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id))
    }

    @Override
    @Transactional
    Employee save(@Valid Employee user) {
        entityManager.persist(user)
        return user
    }

    @Override
    @Transactional
    int update(@Valid Employee user) {
//        return entityManager.createQuery("UPDATE Employee u SET u.firstName = :firstName, u.lastName = :lastName, u.age = :age where u.id = :id")
//                .setParameter("firstName", user.firstName)
//                .setParameter("lastName", user.lastName)
//                .setParameter("age", user.age)
//                .setParameter("id", user.id)
//                .executeUpdate()

        return entityManager.merge(user);
    }

    @Override
    @Transactional
    void deleteById(@NotNull Long id) {
        findById(id).ifPresent { user -> entityManager.remove(user) }
    }

    @Transactional(readOnly = true)
    List<Employee> findAll() {
        String qlString = "SELECT u FROM Employee u"
        TypedQuery<Employee> query = entityManager.createQuery(qlString, Employee.class)
        return query.getResultList()
    }

}
