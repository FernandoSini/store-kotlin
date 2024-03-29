package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.Cart
import com.fernandosini.storeapikotlin.exceptions.InternalServerErrorException
import com.fernandosini.storeapikotlin.services.CartService
import com.fernandosini.storeapikotlin.services.ProductServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody

@Controller
@RequestMapping("/api")
@Tag(name = "Cart Endpoint")
class CartController(
    private val cartServices: CartService,
    private val productServices: ProductServiceImpl
) {
    @GetMapping("/cart/{userId}")
    @Operation(
        summary = "fetch user cart",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(implementation = Cart::class), mediaType = "application/json"))
        )]
    )
    fun getCart(@PathVariable("cartId") cartId: Long): ResponseEntity<Any> {
        try {
            var cart = cartServices.findCurrentCart(cartId) ?: Cart()

            return ResponseEntity.ok(cart)
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)

        }
    }


    @PostMapping("/cart/add")
    @Operation(
        summary = "Add product to cart",

        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(
                Content(
                    schema = Schema(ref = "#/components/schemas/AddToCartEndpoint"),
                    mediaType = "application/json"
                )
            )
        )]
    )
    fun addProductToCart(
        @SwaggerRequestBody(
            description = "teste",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(ref = "#/components/schemas/AddToCartBody"), mediaType = "application/json"
            )]
        ) @RequestBody data: HashMap<String, Any>
    ) {
        var cartId = data["cartId"].toString().toLong()
        var productId = data["productId"].toString().toLong()
        var quantity = data["quantity"].toString().toInt()
        cartServices.addToCart(cartId = cartId, quantity = quantity, productId = productId);
    }


    @PutMapping("/cart/remove")
    @Operation(
        summary = "Add product to cart",

        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(
                Content(
                    schema = Schema(ref = "#/components/schemas/RemoveFromCartEndpoint"),
                    mediaType = "application/json"
                )
            )
        )]
    )
    fun removeProductFromCart(
        @SwaggerRequestBody(
            description = "teste",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(ref = "#/components/schemas/AddToCartBody"), mediaType = "application/json"
            )]
        ) @RequestBody data: HashMap<String, Any>
    ) {

    }

}