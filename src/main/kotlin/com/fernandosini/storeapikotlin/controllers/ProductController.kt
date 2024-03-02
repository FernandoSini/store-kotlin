package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.data.models.User
import com.fernandosini.storeapikotlin.exceptions.CustomExceptionHandler
import com.fernandosini.storeapikotlin.exceptions.InternalServerErrorException
import com.fernandosini.storeapikotlin.services.ProductServices
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody

@Controller
@RequestMapping("/api")
@Tag(name = "Product Endpoint")
class ProductController(private val productService: ProductServices) {


    @GetMapping("/products")
    @Operation(
        summary = "Get product by name and added pagination", responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(ref = "#/components/schemas/ProductEndpoint"))),
        )]
    )
    fun getProducts(

        @RequestParam(required = false) name: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Any> {

        try {
            var products: MutableList<Product> = mutableListOf<Product>()
            val paging = PageRequest.of(page, size)
            val pageProducts: Page<Product> = if (name.isEmpty()) {
                productService.findAll(paging)
            } else {
                productService.findByProductName(productName = name, pageable = paging)
            }
            products = pageProducts.content

            val response = HashMap<String, Any>()
            response["result"] = products
            response["currentPage"] = pageProducts.number
            response["totalItems"] = pageProducts.totalElements
            response["totalPages"] = pageProducts.totalPages
            return ResponseEntity(response, HttpStatus.OK)
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)
        }


    }
}