package com.fernandosini.storeapikotlin.data.models.DTO

import com.fernandosini.storeapikotlin.data.models.Product

data class CartDTO(
    var cartId: Long = -1,
    var totalPrice: Double = 0.0,
    var products: MutableSet<Product> = mutableSetOf<Product>()
) {
}