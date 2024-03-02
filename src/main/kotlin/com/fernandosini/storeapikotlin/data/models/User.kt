package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") var id: Long = -1,
    @Column(name = "username") @Size(min = 2) var username: String = "",
    @Column(name = "birthday") var birthday: String = "",
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "updated_at") var updatedAt: ZonedDateTime,
    @Column(name = "firstname") var firstname: String = "",
    @Column(name = "lastname") var lastname: String = "",
    @Column(name = "email") var email: String = ""
) : Serializable