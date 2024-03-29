package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {

    @Query("select ci from cart_item  ci where ci.cart.id =:cartId and ci.product.id =:productId")
    fun findCartItemByProductIdAndCartId(
        @Param("cartId") cartId: Long,
        @Param("productId") productId: Long
    ): Optional<CartItem>

    @Modifying
    @Query("delete from cart_item  ci where ci.cart.id =:cartId and ci.product.id =:productId")
    fun deleteCartItemByProductIdAndCartId(@Param("cartId") cartId: Long, @Param("productId") productId: Long): Unit
}