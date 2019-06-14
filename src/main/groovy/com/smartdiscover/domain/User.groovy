package com.smartdiscover.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @NotNull
    @Column(name = "first_name", nullable = false)
    String firstName

    @NotNull
    @Column(name = "last_name", nullable = false)
    String lastName

    /*@NotNull
    @Column(name = "email", nullable = false, unique = true)
    String email*/

    @NotNull
    @Column(name = "age")
    int age

    /*@NotNull
    @Column(name = "date_of_birth", nullable = false)
    Date dateOfBirth*/

}
