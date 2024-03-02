package com.fernandosini.storeapikotlin

import com.fernandosini.storeapikotlin.adapters.Mapper
import com.fernandosini.storeapikotlin.data.models.DTO.UserDTO
import com.fernandosini.storeapikotlin.data.models.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile

@Profile("dev")
@SpringBootApplication
class StoreapikotlinApplication

fun main(args: Array<String>) {
    runApplication<StoreapikotlinApplication>(*args)
   /* var user = User();
    user.id = 0
    user.username = "fernando"
    user.age = "10"

    var mapper = Mapper()
    var dto =  mapper.convert(user, UserDTO::class.java)
    println(dto.age);
*/

}
