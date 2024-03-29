package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.*
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.CartRepository
import com.fernandosini.storeapikotlin.repository.OrderItemRepository
import com.fernandosini.storeapikotlin.repository.OrderRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.function.Consumer

@Service
class OrderServiceImpl(
    private val repository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val cartRepository: CartRepository,
    private val cartServices: CartService,
) : OrderService {

    override fun getOrdersByUserId(userId: Long, pageable: Pageable): Page<Order> {
        return repository.findOrdersByUserId(userId, pageable)

    }

    override fun placeOrder(cartId: Long): Unit {

        val cart = cartRepository.findCurrentCart(cartId).orElseThrow { NotFoundException("Cart not found!") }

        if (cart == null) {
            throw NotFoundException("Cart not found!")

        }

        var order: Order = Order();
        order.user = cart.user
        order.orderStatus = OrderStatus.Processing
        order.createdAt = ZonedDateTime.now()
        order.totalAmount = cart.totalPrice
        var payment = Payment()
        var transaction = Transaction()
        var paymentDetails = PaymentDetails()
        payment.user = order.user
        payment.transaction = transaction
        payment.cardProvider = order.user.payments.first().cardProvider
        payment.paymentMethod = order.user.payments.first().paymentMethod
        transaction.userPayment = payment
        transaction.order = order
        transaction.paymentStatus = PaymentStatus.Pending
        order.transaction = transaction
        val savedOrder = repository.saveAndFlush(order)
        var orderItems = mutableSetOf<OrderItems>()

        cart.items.forEach { cartItem ->
            var orderItem: OrderItems =
                OrderItems(
                    product = cartItem.product,
                    order = savedOrder,
                    createdAt = ZonedDateTime.now(),
                    quantity = cartItem.quantity,
                    orderItemPrice = cartItem.productPrice


                )
            orderItems.add(orderItem)
        }
        orderItems = orderItemRepository.saveAllAndFlush(orderItems).toMutableSet()
        cart.items.forEach { cartItem ->
            var product = cartItem.product
            cartServices.deleteProductInCart(cartId, cartItem.product?.id!!)
            product.quantity = product.quantity - cartItem.quantity


        }
        // val orderDTO: OrderDTO = modelMapper.map(savedOrder, OrderDTO::class.java)

        /* orderItems.forEach(Consumer<OrderItems> { item: OrderItems? ->
             orderDTO.getOrderItems().add(
                 modelMapper.map(
                     item,
                     OrderItemDTO::class.java
                 )
             )
         })

         return orderDTO*/

    }

    override fun findOrderById(orderId: Long): Order? {
        return repository.findById(orderId).orElseThrow { NotFoundException("Not found order with this Id!") }
    }

    override fun updateOrder(orderId: Long, userId: Long, orderStatus: OrderStatus) {
        val order = repository.findOrderByOrderIdAndUserId(orderId = orderId, userId = userId)
        if (order == null) {

            throw (NotFoundException("Not found order with this Id!"))
        }
        order.orderStatus = orderStatus
        repository.save(order)
    }

    override fun findAllOrders(pageable: Pageable): Page<Order> {
        return repository.findAll(pageable)
    }

    override fun findAllOrders(filter: String, pageable: Pageable): Page<Order> {
        return repository.findAllByFilter(filter = filter, pageable = pageable)
    }


}