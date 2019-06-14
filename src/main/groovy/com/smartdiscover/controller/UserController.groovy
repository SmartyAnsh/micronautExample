package com.smartdiscover.controller

import com.smartdiscover.domain.User
import com.smartdiscover.repository.UserRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.reactivex.Single
import javax.validation.Valid

@Controller("/user")
class UserController {

    protected final UserRepository userRepository

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Get("/")
    Single<String> index(String name) {
        return Single.just("Hello World!")
    }

    @Post("/")
    Single<User> save(@Body @Valid cmd) {
        println cmd
        User user = userRepository.save(cmd.firstName, cmd.lastName, cmd.age)
        return Single.just(user)
    }


    @Get
    def read(){

    }

    @Put
    def update(){

    }

    @Delete
    def delete(){

    }



}
