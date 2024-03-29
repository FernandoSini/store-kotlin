package com.fernandosini.storeapikotlin.config

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.data.models.User
import io.swagger.v3.oas.annotations.OpenAPI31
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.StringSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.ZonedDateTime
import kotlin.collections.ArrayList

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Kotlin store",
        version = "1.0.0",
        description = "",
        license = License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
        contact = Contact(email = "sinigagliafernando@gmail.com", name = "Contact me"),

        )
)
class SwaggerConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        val loginSchema = ObjectSchema().name("LoginEndpoint")
            .title("LoginEndpoint")
            .description("login description")
            .addProperty("Login", StringSchema().example("usuario"))
            .addProperty("Password", StringSchema().example("abc123"))
        val registerSchemaResponse = ObjectSchema().name("RegisterEndpoint")
            .title("RegisterEndpoint")
            .description("register description")
            .addProperty("message", StringSchema().example("Registered successfully!"))
            .addProperty(
                "result", ArraySchema().example(
                    arrayOf(
                        User(
                            username = "teste",
                            email = "teste@teste.com",
                            id = 123,
                            firstname = "teste",
                            lastname = "teste",
                            createdAt = ZonedDateTime.now(),
                            updatedAt = ZonedDateTime.now(),
                            birthday = "01/02/2003",
                        )
                    )
                )
            )
        val productSchemaResponse = ObjectSchema().name("ProductEndpoint")
            .title("ProductEndpoint")
            .description("product description")
            .addProperty("currentPage", StringSchema().example(0))
            .addProperty("totalItems", StringSchema().example(4))
            .addProperty("totalPages", StringSchema().example(10))
            .addProperty(
                "result", ArraySchema().example(emptyArray<Any>())
            )
        val productCreateSchemaResponse = ObjectSchema().name("ProductCreateEndpoint")
            .title("ProductCreateEndpoint")
            .description("product description")
            .addProperty(
                "result", ArraySchema().example(emptyArray<Any>())
            )
            .addProperty("message", StringSchema().example("Product created successfully!"))

        val cartPaginationSchemaResponse = ObjectSchema().name("CartPaginationEndpoint")
            .title("CartEndpoint")
            .description("cart description")
            .addProperty("currentPage", StringSchema().example(0))
            .addProperty("totalItems", StringSchema().example(4))
            .addProperty("totalPages", StringSchema().example(10))
            .addProperty(
                "result", ArraySchema().example(emptyArray<Any>())
            )
        val addToCartSchemaBody = ObjectSchema().name("AddToCartBody")
            .title("AddToCartBody")
            .description("cart description")
            .addProperty("cartId", StringSchema().example(0))
            .addProperty("userId", StringSchema().example(0))
            .addProperty("productId", StringSchema().example(0))
            .addProperty("quantity", StringSchema().example(0))
            //.addProperty("product", ObjectSchema().example(emptyMap<String, Any>()))

        val addToCartSchemaResponse = ObjectSchema().name("AddToCartEndpoint")
            .title("AddToCartEndpoint")
            .description("cart description")
            .addProperty("message", StringSchema().example("Product added to cart!"))
            .addProperty("result", ObjectSchema().example(emptyMap<String, Any>()))

        val removeFromCartSchemaResponse = ObjectSchema().name("RemoveFromCartEndpoint")
            .title("RemoveFromCartEndpoint")
            .description("cart description")
            .addProperty("message", StringSchema().example("Product removed from cart successfully!"))
            .addProperty("result", ObjectSchema().example(emptyMap<String,Any>()))


        val orderSchemaResponse = ObjectSchema().name("OrderEndpoint")
            .title("OrderEndpoint")
            .description("Order description")
            .addProperty(
                "result", ObjectSchema().example({})
            )

        val orderPaginationSchemaResponse = ObjectSchema().name("OrderPaginationEndpoint")
            .title("OrderEndpoint")
            .description("Order description")
            .addProperty("currentPage", StringSchema().example(0))
            .addProperty("totalItems", StringSchema().example(4))
            .addProperty("totalPages", StringSchema().example(10))
            .addProperty(
                "result", ArraySchema().example(emptyArray<Any>())
            )
        val historySchemaResponse = ObjectSchema().name("HistoryEndpoint")
            .title("HistoryEndpoint")
            .description("History description")
            .addProperty("currentPage", StringSchema().example(0))
            .addProperty("totalItems", StringSchema().example(4))
            .addProperty("totalPages", StringSchema().example(10))
            .addProperty(
                "result", ArraySchema().example(emptyArray<Any>())
            )
        val userExample = User(
            username = "teste",
            email = "teste@teste.com",
            id = 123,
            firstname = "teste",
            lastname = "teste",
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
            birthday = "01/02/2003"
        )
        val userProfileSchemaResponse = ObjectSchema().name("UserProfileEndpoint")
            .title("UseProfileEndpoint")
            .description("User description")
            .addProperty(
                "result",
                ObjectSchema()
                    .addProperty("username", StringSchema().example(userExample.username))
                    .addProperty("id", IntegerSchema().example(userExample.id))
                    .addProperty("lastname", StringSchema().example(userExample.lastname))
                    .addProperty("birthday", StringSchema().example(userExample.birthday))
                    .addProperty("created_at", StringSchema().example(userExample.createdAt.toLocalDateTime()))
                    .addProperty("updated_at", StringSchema().example(userExample.updatedAt?.toLocalDateTime()))
            )

        return OpenAPI().components(
            Components()
                .addSchemas(loginSchema.name, loginSchema)
                .addSchemas(registerSchemaResponse.name, registerSchemaResponse)
                .addSchemas(productSchemaResponse.name, productSchemaResponse)
                .addSchemas(cartPaginationSchemaResponse.name, cartPaginationSchemaResponse)
                .addSchemas(orderSchemaResponse.name, orderSchemaResponse)
                .addSchemas(historySchemaResponse.name, historySchemaResponse)
                .addSchemas(userProfileSchemaResponse.name, userProfileSchemaResponse)
                .addSchemas(productCreateSchemaResponse.name, productCreateSchemaResponse)
                .addSchemas(addToCartSchemaResponse.name, addToCartSchemaResponse)
                .addSchemas(removeFromCartSchemaResponse.name, removeFromCartSchemaResponse)
                .addSchemas(addToCartSchemaBody.name, addToCartSchemaBody)
                .addSchemas(orderPaginationSchemaResponse.name, orderPaginationSchemaResponse)
        )

    }
}