package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity(name = "transaction")
class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @OneToOne @JoinColumn(name = "fk_order_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var order: Order? = null,
    @JsonIgnoreProperties(value = ["user"])
    @OneToOne
    @JoinColumn(name = "fk_payment_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var userPayment: Payment? = null,
   /* @OneToOne
    @JoinColumn(name = "fk_payment_details_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var paymentDetails: PaymentDetails? = null,*/
    @Column(name="payment_status")
    var paymentStatus:PaymentStatus? = null
) {

}