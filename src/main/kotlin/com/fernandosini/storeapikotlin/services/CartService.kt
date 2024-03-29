package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.Cart
import com.fernandosini.storeapikotlin.data.models.DTO.CartDTO

interface CartService {

    fun findCurrentCart(cartId: Long): Cart?
    fun addToCart(productId: Long, cartId: Long, quantity: Int): CartDTO
    fun getCart(userId: Long, cartId: Long): CartDTO
    fun updateProductInCart(cartId: Long, productId: Long)
    fun updateProductQuantityInCart(cartId: Long, productId: Long, quantity: Integer): CartDTO
    fun deleteProductInCart(cartId: Long, productId: Long)
}