package com.smartdiscover.repository

import com.smartdiscover.domain.Employee

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

interface EmployeeRepository {

    Optional<Employee> findById(@NotNull Long id)

    Employee save(@Valid Employee user)

    int update(@Valid Employee user)

    void deleteById(@NotNull Long id)

    List<Employee> findAll()
}