package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") var id: Long = -1,
    @Column(name = "username") @Size(min = 2) var username: String = "",
    @Column(name = "birthday") var birthday: String = "",
    @Column(name = "created_at") var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(name = "updated_at") var updatedAt: ZonedDateTime? = null,
    @Column(name = "firstname") var firstname: String = "",
    @Column(name = "lastname") var lastname: String = "",
    @Column(name = "email") var email: String = "",
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    @OneToMany(mappedBy = "user") val payments: MutableSet<Payment> = mutableSetOf<Payment>(),
    @JsonIgnore
    @OneToMany(mappedBy = "user") val orders: MutableSet<Order> = mutableSetOf<Order>(),
    /* @JsonIgnore
     @OneToOne(mappedBy = "user") var shoppingSession: ShoppingSession? = null,*/
) : Serializable