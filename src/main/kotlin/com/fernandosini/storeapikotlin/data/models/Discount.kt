package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime


@Entity(name = "discount")
class Discount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "discount_id") var id: Long = -1,
    //@OneToMany(mappedBy = "discount") var products: MutableSet<Product>?,
    @OneToMany(mappedBy = "discount") var items: MutableSet<CartItem>? = null,
    @Column(name = "discount_code") var discountCode: String? = null,
    @Column(name = "discount_percent") var discountPercent: Long? = null,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "modified_at") var modifiedAt: ZonedDateTime,
    @Column(name = "deleted_at") var deletedAt: ZonedDateTime
) : Serializable {
}