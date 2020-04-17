package com.smartdiscover.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Entity
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @NotNull
    String firstName

    @NotNull
    String lastName

    @Email
    String email

}
