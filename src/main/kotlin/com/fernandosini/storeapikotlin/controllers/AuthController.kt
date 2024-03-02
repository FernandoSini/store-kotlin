package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.User
import com.fernandosini.storeapikotlin.services.UserServices
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperties
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.kafka.common.protocol.types.ArrayOf
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody

@Controller
@RequestMapping("/api")
@Tag(name = "Authentication EndPoint")
class AuthController(private val userServices: UserServices) {


    @PostMapping("/login")
    @Operation(
        summary = "Make login on site",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(implementation = User::class)))
        )]
    )
    fun login(
        @SwaggerRequestBody(
            description = "",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(ref = "#/components/schemas/LoginEndpoint")
            )]
        ) @RequestBody data: HashMap<String, Any>
    ) {

    }

    @PostMapping("/register")
    @Operation(
        summary = "Register on site",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(ref = "#/components/schemas/RegisterEndpoint")))
        )]
    )
    fun register(
        @SwaggerRequestBody(
            description = "",
            useParameterTypeSchema = false,
            content = [Content(
                schema = Schema(implementation = User::class)
            )]
        )
        @RequestBody data: HashMap<String, Any>
    ) {
    }
}