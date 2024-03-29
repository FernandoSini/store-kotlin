package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.Order
import com.fernandosini.storeapikotlin.data.models.OrderStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderService {

    fun getOrdersByUserId(userId: Long, pageable: Pageable): Page<Order>
    fun placeOrder(cartId: Long): Unit
    fun findOrderById(orderId: Long): Order?
    fun updateOrder(orderId: Long, userId: Long, orderStatus: OrderStatus)
    fun findAllOrders(pageable: Pageable): Page<Order>
    fun findAllOrders(filter: String, pageable: Pageable): Page<Order>

}