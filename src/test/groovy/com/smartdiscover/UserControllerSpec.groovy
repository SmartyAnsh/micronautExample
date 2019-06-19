package com.smartdiscover

import com.smartdiscover.domain.User
import groovy.json.JsonSlurper
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class UserControllerSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    UserClient client

    void "test index"() {
        expect:
        client.index() == "Hello World!"
    }

    void "test save"() {
        when:
        def user = new User("firstName": "smart", "lastName": "discover", "age": 29)
        def savedUser = client.save(user)
        def resp = client.read(savedUser.id)
        then:
        assert resp.contains("id") == true
    }

    void "test read"() {
        when:
        def user = new User("firstName": "smartAnshul", "lastName": "discover", "age": 29)
        def savedUser = client.save(user)
        def resp = client.read(savedUser.id)
        then:
        assert resp.contains("smartAnshul") == true
        assert resp.contains("email") == false
    }


    void "test readAll"() {
        when:
        def resp = client.readAll()
        def user = new JsonSlurper().parseText(resp)
        then:
        user.size() > 15
    }

    /*
    void "test update"() {
        when:
        def user = new User("firstName": "smart", "lastName": "discover", "age": 29)
        def savedUser = client.save(user)
        user = new User(id: savedUser.id, "firstName": "smartyyy", "lastName": "discover", "age": 29)
        def resp = client.update(user)
        then:
        assert resp.contains("smartyyy")
    }

    void "test delete"() {
        when:
        def user = new User("firstName": "smart", "lastName": "discover", "age": 29)
        def savedUser = client.save(user)
        def resp = client.delete(savedUser.id)
        then:
        assert resp.size() == 6
    }*/
}
