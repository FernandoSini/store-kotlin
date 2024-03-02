package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import java.time.ZonedDateTime

@Entity(name = "cart")
class Cart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "cart_id") var id: Long = -1,
    @Column(name = "user_id") var userId: Long,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "updated_at") var updatedAt: ZonedDateTime,
    @OneToMany(mappedBy = "cart") var products: MutableSet<Product>

) {
}