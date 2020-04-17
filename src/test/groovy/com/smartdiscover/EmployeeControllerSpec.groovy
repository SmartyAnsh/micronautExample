package com.smartdiscover

import com.smartdiscover.domain.Employee
import groovy.json.JsonSlurper
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class EmployeeControllerSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    EmployeeClient client

    void "test index"() {
        expect:
        client.index() == "Hello World!"
    }

    void "test save"() {
        when:
        def user = new Employee("firstName": "smart", "lastName": "discover", "email": "anshul@email.com")
        def savedEmployee = client.save(user)
        def resp = client.read(savedEmployee.id)
        then:
        assert resp.contains("id") == true
    }

    void "test read"() {
        when:
        def user = new Employee("firstName": "smartAnshul", "lastName": "discover", "email": "anshul@email.com")
        def savedEmployee = client.save(user)
        def resp = client.read(savedEmployee.id)
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

    void "test update"() {
        when:
        def user = new Employee("firstName": "smart", "lastName": "discover", "email": "anshul@email.com")
        def savedEmployee = client.save(user)
        user = new Employee(id: savedEmployee.id, "firstName": "smartyyy", "lastName": "discover", "email": "anshul@email.com")
        def resp = client.update(user)
        then:
        assert resp.contains("smartyyy")
    }

    void "test delete"() {
        when:
        def user = new Employee("firstName": "smart", "lastName": "discover", "email": "anshul@email.com")
        def savedEmployee = client.save(user)
        def resp = client.delete(savedEmployee.id)
        then:
        assert resp.size() == 6
    }
}
