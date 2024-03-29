package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductService {
     fun findById(productId: Long): Product?
    fun findAll(pageable: Pageable): Page<Product>
    fun findByProductName(productName: String, pageable: Pageable): Page<Product>
    fun createProduct(product: Product): Product?
}