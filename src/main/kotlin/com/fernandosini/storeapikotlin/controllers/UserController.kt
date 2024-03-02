package com.fernandosini.storeapikotlin.controllers

import com.fernandosini.storeapikotlin.data.models.User
import com.fernandosini.storeapikotlin.services.UserServices
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.kafka.common.message.DescribeUserScramCredentialsRequestData.UserName
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api")
@Tag(name = "User Endpoint")
class UserController(private val userServices: UserServices) {


    @GetMapping("/user/{id}")
    @Operation(
        summary = "Find user by id",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(schema = Schema(ref = "#/components/schemas/UserProfileEndpoint")))
        )]

    )
    fun findUser(
        @PathVariable("id") id: Long
    ) {
        val user = userServices.findUserById(id)


        val response = HashMap<String, Any>()
        response["result"] = user

    }
}