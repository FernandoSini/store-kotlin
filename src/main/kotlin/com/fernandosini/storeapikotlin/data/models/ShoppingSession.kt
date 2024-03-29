package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "shopping_session")
class ShoppingSession(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "session_id") var id: Long = -1,
    @JsonIgnore @OneToOne @JoinColumn(
        name = "fk_user_id",
        foreignKey = ForeignKey(ConstraintMode.CONSTRAINT)
    ) var user: User,
    @Column(name = "total") var total: Double,
    @Column(name = "amountItems") var amountItems: Long? = null,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "modified_at") var modifiedAt: ZonedDateTime,
    //@OneToMany(mappedBy = "shoppingSession") var carts: MutableSet<Cart>,
    //@JsonIgnore @OneToOne(mappedBy = "shoppingSession") var order: Order,
) : Serializable {}