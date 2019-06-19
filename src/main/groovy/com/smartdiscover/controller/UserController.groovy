package com.smartdiscover.controller

import com.smartdiscover.domain.User
import com.smartdiscover.repository.UserRepository
import io.micronaut.http.annotation.*
import io.reactivex.Single

import javax.validation.Valid

@Controller("/user")
class UserController {

    protected final UserRepository userRepository

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Get("/")
    Single<String> index() {
        return Single.just("Hello World!")
    }

    @Get("/{id}")
    def read(Long id) {
        def user = userRepository.findById(id)
        return Single.just(user)
    }

    @Post("/")
    Single<User> save(@Body @Valid User userParams) {
        println userParams
        User savedUser = userRepository.save(userParams.firstName, userParams.lastName, userParams.age)
        return Single.just(savedUser)
    }

    @Put("/")
    def update(@Body @Valid User userParams) {
        def updatedRows = userRepository.update(userParams)
        read(userParams.id)
    }

    @Delete("/{id}")
    def delete(Long id) {
        userRepository.deleteById(id)
        readAll()
    }

    @Get("/readAll")
    def readAll() {
        def users = userRepository.findAll()
        return users
    }


}
