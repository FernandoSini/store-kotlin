package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*

@Entity(name = "cart_item")
class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    var itemId: Long? = -1,
    @ManyToOne @JoinColumn(name = "fk_product_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var product: Product = Product(),
    @ManyToOne @JoinColumn(name = "fk_cart_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var cart: Cart? = null,
    @Column(name = "quantity")
    var quantity :Int = 0,
    @ManyToOne
    @JoinColumn(name = "fk_discount_id", foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var discount: Discount? = null,
    @Column(name = "product_price")
    var productPrice:Double = 0.0

) {
}