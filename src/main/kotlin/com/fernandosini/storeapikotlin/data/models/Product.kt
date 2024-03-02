package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.ZonedDateTime

@Entity(name = "product")
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "productId") var id: Long = -1,
    @Column(name = "quantity") var quantity: Int,
    @Column(name = "price") var price: Double,
    @Column(name = "name") var name: String,
    @Column(name = "discount_code") var discountCode: String,
    @Column(name = "image_url") var imageUrl: String,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "updated_at") var updatedAt: ZonedDateTime,
    @ManyToOne @JoinColumn(name = "cart_id") var cart: Cart,
    @ManyToOne @JoinColumn(name = "inventory_id") var inventory: ProductInventory,
    @ManyToOne @JoinColumn(name = "discount_id") var discount: Discount

) {
}