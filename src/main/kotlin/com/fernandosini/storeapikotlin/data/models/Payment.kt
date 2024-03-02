package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*

@Entity(name = "Payment")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = -1
) {
}