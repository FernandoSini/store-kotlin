package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.Cart
import com.fernandosini.storeapikotlin.data.models.Order
import com.fernandosini.storeapikotlin.exceptions.InternalServerErrorException
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.OrderRepository
import com.fernandosini.storeapikotlin.services.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.security.access.annotation.Secured;

@Controller
@RequestMapping("/api")
@Tag(name = "Order Endpoint")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/orders/{userId}")
    @Operation(
        summary = "Fetch order",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(implementation = Order::class), mediaType = "application/json"))
        )]
    )
    fun getOrdersByUserId(
        @PathVariable("userId") userId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Any> {

        try {
            var orders = mutableSetOf<Order>()
            var paging = PageRequest.of(page, size)
            val pageOrders: Page<Order> = orderService.getOrdersByUserId(userId = userId, pageable = paging)
            if (pageOrders.content.isEmpty()) {
                throw NotFoundException("Not found orders for this users")
            }
            orders = pageOrders.content.toMutableSet()

            val response = HashMap<String, Any>()
            response["result"] = orders
            response["totalPages"] = pageOrders.totalPages;
            response["totalItems"] = pageOrders.totalElements;
            response["currentPage"] = pageOrders.number;
            return ResponseEntity.ok(response);
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)
        }
    }

    @GetMapping("/order/{orderId}")
    @Operation(
        summary = "Fetch users order",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(
                Content(
                    array = ArraySchema(schema = Schema(implementation = Order::class)),
                    mediaType = "application/json"
                )
            )
        )]
    )
    fun getOrderById(@PathVariable("orderId") orderId: Long): ResponseEntity<Any> {
        try {
            var order: Order? =
                orderService.findOrderById(orderId) ?: throw NotFoundException("Not found order with this Id!")
            return ResponseEntity(order, HttpStatus.OK)
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)
        }
    }

    @PostMapping("/order/place/{cartId}")
    @Operation(
        summary = "Place current order",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(
                Content(
                    array = ArraySchema(schema = Schema(implementation = Order::class)),
                    mediaType = "application/json"
                )
            )
        )]
    )
    fun placeOrder(@PathVariable("cartId") cartId: Long) {

        try {
            var order = orderService.placeOrder(cartId = cartId)


        } catch (e: Exception) {

            throw InternalServerErrorException(e.message!!)
        }
    }

    @Secured(value = ["admin"])
    fun getAllOrders(
        @RequestParam(required = false) filter: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Any> {

        try {
            var pageable = PageRequest.of(page, size)
            var orders = mutableSetOf<Order>();
            var pageOrders: Page<Order> = if (filter.isEmpty()) {
                orderService.findAllOrders(pageable)
            } else {
                orderService.findAllOrders(filter, pageable)
            }
            orders = pageOrders.content.toMutableSet()

            val response = HashMap<String, Any>()
            response["result"] = orders
            response["currentPage"] = pageOrders.number
            response["totalItems"] = pageOrders.totalElements
            response["totalPages"] = pageOrders.totalPages
            return ResponseEntity(response, HttpStatus.OK)

        } catch (e: Exception) {

            throw InternalServerErrorException(e.message!!);
        }
    }
}