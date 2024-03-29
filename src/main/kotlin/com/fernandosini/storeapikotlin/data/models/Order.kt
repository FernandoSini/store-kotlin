package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "order_table")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = -1,
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "fk_user_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var user: User = User(),
    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = null,
    @Column(name = "modified_at")
    var modifiedAt: ZonedDateTime? = null,
    @Enumerated
    @Column(name = "order_status")
    var orderStatus: OrderStatus? = null,
    @Column(name = "order_description")
    var orderDescription: String = "",
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    var orderItems: MutableSet<OrderItems> = mutableSetOf<OrderItems>(),
    @Column(name = "total_amount")
    var totalAmount: Double = 0.0,
    @OneToOne(mappedBy = "order")
    var transaction: Transaction? = null


) : Serializable {
}