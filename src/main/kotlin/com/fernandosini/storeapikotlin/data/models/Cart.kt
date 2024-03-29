package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "cart")
class Cart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "cart_id") var id: Long = -1,
    @JsonIgnore
    @ManyToOne @JoinColumn(
        name = "fk_user_id",
        foreignKey = ForeignKey(ConstraintMode.CONSTRAINT)
    ) var user: User = User(),
    @Column(name = "created_at") var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(name = "updated_at") var updatedAt: ZonedDateTime? = null,
    @OneToMany(mappedBy = "cart") var items: MutableSet<CartItem> = mutableSetOf<CartItem>(),
    @Column(name = "total_price") var totalPrice: Double = 0.0
) : Serializable {
}