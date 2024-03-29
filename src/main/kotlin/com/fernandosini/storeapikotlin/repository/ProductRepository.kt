package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.data.models.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    override fun findAll(pageable: Pageable): Page<Product>
    fun findProductByName(name: String, pageable: Pageable): Page<Product>
}