package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {

    @Query("select o from order_table o where o.user.id =: userId")
    fun findOrdersByUserId(@Param("user_id") userId: Long, pageable: Pageable): Page<Order>


    @Query("select o from order_table o where o.id =: orderId and o.user.id=:userId")
    fun findOrderByOrderIdAndUserId(@Param("orderId") orderId: Long, @Param("userId") userId: Long): Order

    fun findAllByFilter(@Param("filter") filter:String, pageable: Pageable): Page<Order>
}