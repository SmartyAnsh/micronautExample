package com.smartdiscover.repository

import com.smartdiscover.domain.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

interface UserRepository {

    Optional<User> findById(@NotNull Long id)

    User save(@NotBlank String firstName, @NotBlank String lastName, @NotBlank int age)

    //void deleteById(@NotNull Long id)

    //List<User> findAll(@NotNull SortingAndOrderArguments args)

    //int update(@NotNull Long id, @NotBlank String name)
    
}