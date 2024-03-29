package com.fernandosini.storeapikotlin.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "product")
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "productId") var id: Long = -1,
    @Column(name = "price") var price: Double = 0.0,
    @Column(name = "name") var name: String? = null,
    @Column(name = "image_url") var imageUrl: String? = null,
    @Column(name = "created_at") var createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(name = "updated_at") var updatedAt: ZonedDateTime = ZonedDateTime.now(),
    @JsonIgnore
    @OneToMany(mappedBy = "product") var cartItems: MutableSet<CartItem>? = null,
    @Column(name = "quantity") var quantity: Int = 0,
    @JsonIgnoreProperties(value = ["products"])
  /*  @ManyToOne
    @JoinColumn(name = "fk_discount_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var discount: Discount? = null,*/

    @JsonIgnore
    @OneToOne(mappedBy = "product")
    var orderItems: OrderItems? = null

) : Serializable {
}