package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServices(private val productRepository: ProductRepository) {


    fun findAll(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }

    fun findByProductName(productName: String, pageable: Pageable): Page<Product> {
        return productRepository.findProductByName(productName, pageable)
    }
}