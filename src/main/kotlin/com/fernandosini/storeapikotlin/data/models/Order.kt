package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity(name = "order_table")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = -1,
    @Column(name = "fk_user_id")
    var userId: Long = -1,
    @Column(name = "fk_cart_id")
    var cartId: Long = -1,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Enumerated
    @Column(name = "order_status")
    var orderStatus: OrderStatus,
    @Column(name = "order_description")
    var orderDescription: String,


    ) {
}