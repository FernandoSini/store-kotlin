package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "order_items")
class OrderItems(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_items_id") var id: Long = -1,
    @ManyToOne @JoinColumn(name = "order_id") var order: Order = Order(),
    @OneToOne @JoinColumn(name = "product_id") var product: Product = Product(),
    @Column(name = "quantity") var quantity: Int = 0,
    @Column(name = "created_at") var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(name = "modified_at") var modifiedAt: ZonedDateTime? = null,
    @Column(name = "deleted_at") var deletedAt: ZonedDateTime? = null,
    @Column(name = "product_price") var orderItemPrice: Double = 0.0,
) : Serializable {
}