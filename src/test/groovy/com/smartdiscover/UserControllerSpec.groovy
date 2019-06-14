package com.smartdiscover

import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class UserControllerSpec extends Specification {

    @Inject
    UserClient client

    void "test index"() {
        expect:
        client.index() == "Hello World!"
    }
}
