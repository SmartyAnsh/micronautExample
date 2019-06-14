package com.smartdiscover

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client('/user')
interface UserClient {

    @Get("/}")
    String index()
}