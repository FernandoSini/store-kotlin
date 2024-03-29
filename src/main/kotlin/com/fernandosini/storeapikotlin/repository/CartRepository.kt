package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.Cart
import com.fernandosini.storeapikotlin.data.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : JpaRepository<Cart, Long> {

    @Query("select c from cart c where c.id =:cartId")
    fun findCurrentCart(@Param("cartId") cartId: Long): Optional<Cart>
    //@Modifying
    //  @Query(value = "insert into cart values (?)")
    //  fun addToCart(product: Product): Unit

    @Query("select c from cart c where c.user.id =: userId and c.id =: cartId")
    fun findCartByUserIdAndCartId(@Param("userId") userId: Long, @Param("cartId") cartId: Long): Cart

   fun deleteProductFromCart(productId:Long, cartId: Long)
}