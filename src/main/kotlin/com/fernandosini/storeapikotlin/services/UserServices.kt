package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.User
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServices(private val userRepo: UserRepository) {

    fun findUserById(id: Long): User {
        return userRepo.findById(id).orElseThrow { NotFoundException("User not found!") }
    }

}