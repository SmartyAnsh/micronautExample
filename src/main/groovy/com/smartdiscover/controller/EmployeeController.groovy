package com.smartdiscover.controller

import com.smartdiscover.domain.Employee
import com.smartdiscover.repository.EmployeeRepository
import io.micronaut.http.annotation.*
import io.reactivex.Single

import javax.validation.Valid

@Controller("/employee")
class EmployeeController {

    protected final EmployeeRepository userRepository

    EmployeeController(EmployeeRepository userRepository) {
        this.userRepository = userRepository
    }

    @Get("/{id}")
    def read(Long id) {
        def user = userRepository.findById(id)
        return Single.just(user)
    }

    @Post("/")
    Single<Employee> save(@Body @Valid Employee userParams) {
        println userParams
        Employee savedEmployee = userRepository.save(userParams)
        return Single.just(savedEmployee)
    }

    @Put("/")
    def update(@Body @Valid Employee userParams) {
        def updatedRows = userRepository.update(userParams)
        read(userParams.id)
    }

    @Delete("/{id}")
    def delete(Long id) {
        userRepository.deleteById(id)
        readAll()
    }

    @Get("/")
    def readAll() {
        def users = userRepository.findAll()
        return users
    }


}
