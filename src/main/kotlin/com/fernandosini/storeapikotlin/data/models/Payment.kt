package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "Payment")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    var id: Long = -1,
    @ManyToOne @JoinColumn(name = "fk_user_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var user: User = User(),
    @Enumerated
    @Column(name = "payment_method") var paymentMethod: PaymentMethod = PaymentMethod.CreditCard,
    @Size(min = 4)
    @Column(name = "account_number") var accountNumber: Long? = null,
    @Column(name = "card_provider") var cardProvider: String = "",
    @Column(name = "expires_at") var expiresAt: String = "",
    @OneToOne(mappedBy = "userPayment") var transaction: Transaction? = null,
) : Serializable {
}