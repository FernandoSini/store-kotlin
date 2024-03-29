package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.exceptions.InternalServerErrorException
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.services.ProductServiceImpl
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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.ZonedDateTime
import kotlin.jvm.Throws
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody


@Controller
@RequestMapping("/api")
@Tag(name = "Product Endpoint")
class ProductController(private val productService: ProductServiceImpl) {


    @GetMapping("/products")
    @Operation(
        summary = "Get product by name and added pagination", responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(ref = "#/components/schemas/ProductEndpoint"), mediaType = "application/json")),
        )]
    )
    fun getProducts(

        @RequestParam(required = false) name: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): ResponseEntity<Any> {

        try {
            var products: MutableSet<Product> = mutableSetOf<Product>()
            val paging = PageRequest.of(page, size)
            val pageProducts: Page<Product> = if (name.isEmpty()) {
                productService.findAll(paging)
            } else {
                productService.findByProductName(productName = name, pageable = paging)
            }
            products = pageProducts.content.toMutableSet()

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


    @Throws(Exception::class)
    @PostMapping("/product/create")
    @Operation(
        summary = "Create product", responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(ref = "#/components/schemas/ProductCreateEndpoint"), mediaType = "application/json")),
        )]
    )
    fun createProduct(
        @SwaggerRequestBody(
            description = "",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(implementation = Product::class), mediaType = "application/json"
            )]
        ) @RequestBody data: HashMap<String, Any>
    ): ResponseEntity<Any> {
        var response = HashMap<String, Any>()

        /*var product = Product(
            name = data.getValue("name").toString(),
            price = data.get("price").toString().toDouble(),

            inventory = ProductInventory(
                quantity = (data.getValue("inventory") as HashMap<String, Any>).get("quantity").toString().toLong(),
                createdAt = ZonedDateTime.now(),

                ) ?: null
        )*/
        var imageUrl: String? = null;
        if (data.get("imageBytes") != null) {
            imageUrl = ""
        }
        var product = Product(
            name = data.get("name").toString(),
            price = data.get("price").toString().toDouble(),
            imageUrl = imageUrl ?: null,
            createdAt = ZonedDateTime.now(),

            )
       /* var inventory = ProductInventory(
            product = product, quantity = data.get("quantity").toString().toLong() ?: 0,
            createdAt = ZonedDateTime.now(),
        )
        product.inventory = inventory;*/
        try {
            var productResult = productService.createProduct(product)
            if (productResult != null) {
                response.put("result", {})
                response.put("message", "Product created successfully")

                return ResponseEntity(response, HttpStatus.OK)
            } else {
                throw InternalServerErrorException("Product can't be null")
            }
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)
        }
    }

    @Throws(Exception::class)
    @GetMapping("/product/{id}")
    @Operation(
        summary = "Fetch single product by id", responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(implementation = Product::class), mediaType = "application/json")),
        )]
    )
    fun findSingleProduct(
        @SwaggerRequestBody(
            description = "",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(), mediaType = "application/json"
            )]
        ) @PathVariable("id") id: Long
    ): ResponseEntity<Any> {
        var response = HashMap<String, Any>()
        try {
            var product = productService.findById(id)
            if (product != null) {
                response.put("result", product)
                response.put("message", "")

                return ResponseEntity(response, HttpStatus.OK)
            } else {
                throw NotFoundException("Not found this product!")
            }
        } catch (e: Exception) {
            throw InternalServerErrorException(e.message!!)
        }
    }
}