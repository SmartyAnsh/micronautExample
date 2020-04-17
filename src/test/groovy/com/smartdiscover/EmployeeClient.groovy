package com.smartdiscover

import com.smartdiscover.domain.Employee
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Delete
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

import javax.validation.Valid

@Client('/employee')
interface EmployeeClient {

    @Get("/")
    String index()

    @Get("/{id}")
    String read(Long id)

    @Post("/")
    def save(@Body @Valid Employee userParams)

    @Put("/")
    String update(@Body @Valid Employee userParams)

    @Delete("/{id}")
    String delete(Long id)

    @Get("/readAll")
    String readAll()
}