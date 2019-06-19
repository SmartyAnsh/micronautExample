package com.smartdiscover

import com.smartdiscover.domain.User
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Delete
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

import javax.validation.Valid

@Client('/user')
interface UserClient {

    @Get("/")
    String index()

    @Get("/{id}")
    String read(Long id)

    @Post("/")
    def save(@Body @Valid User userParams)

    @Put("/")
    String update(@Body @Valid User userParams)

    @Delete("/{id}")
    String delete(Long id)

    @Get("/readAll")
    String readAll()
}