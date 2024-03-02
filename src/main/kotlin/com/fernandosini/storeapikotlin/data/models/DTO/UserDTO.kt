package com.fernandosini.storeapikotlin.data.models.DTO

import jakarta.validation.constraints.Size
import java.io.Serializable


// função lambda
val teste = {}
val teste3 =
    linha@{} // isso é pra caso retornemos mais de um valor na funcao lambda mas no retorno tem que ter o return@linha diferente da funcao anonima que pode retornar mais de um valor normalmente

//funcao anonima
val teste2 = fun() {}
 //.let usa o it o .run não precisa usar it pq usa o this então em vez de ser this.length pode ser só length enquanto no let precisa usar it.length
//o .apply é parecido com o let só que é usado pra mudar parametros do objeto pq ele usa o this tbm como o run e o .with tbm
//agr o .also é parecido com o .let
//o .apply e o .also retornam o objeto de contexto
//ja o .let .run e o .with retornam o resultado do lambda
data class UserDTO(
    val id: Int? = null,
    val username: String? = null,
    val age: String? = null

): Serializable