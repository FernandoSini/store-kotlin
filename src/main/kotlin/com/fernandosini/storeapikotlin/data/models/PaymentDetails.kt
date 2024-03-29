package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "payment_details")
class PaymentDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_details_id")
    var id: Long = -1,
    // @OneToOne @JoinColumn(name = "fk_order_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    //var order: Order,
    // @Column(name = "amount")
    //var amount: Long,
    @Enumerated
    @Column(name = "payment_status")
    var paymentStatus: PaymentStatus? = null,
    @Column(name = "created_at") var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(name = "modified_at") var modifiedAt: ZonedDateTime? = null,
  //  @Column(name = "card_provider") var cardProvider: String = "",
   // @OneToOne(mappedBy = "paymentDetails") var transaction: Transaction? = null
) : Serializable {
}