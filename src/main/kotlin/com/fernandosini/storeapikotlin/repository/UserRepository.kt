package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    override fun findById(id: Long): Optional<User> {
        TODO("Not yet implemented")
    }

    fun findUsersBy(username: String, pageable: Pageable): Page<User> {
        TODO("Not yet implemented")
    }
}