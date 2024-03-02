package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity(name = "History")
class History(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "history_id")  var id: Long = -1,
    @Column(name = "fk_order_Id") private var orderId: Long = -1,
    @Column(name = "fk_user_id") private var userId: Long = -1,
    @Column(name = "created_at") private var createdAt: ZonedDateTime

) {
}