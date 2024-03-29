package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.data.models.Product
import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(private val productRepository: ProductRepository):ProductService {

    override fun findById(productId: Long): Product? {
        return productRepository.findById(productId).orElseThrow { NotFoundException("Product Not found!")};
    }

    override fun findAll(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }

    override fun findByProductName(productName: String, pageable: Pageable): Page<Product> {
        return productRepository.findProductByName(productName, pageable)
    }

    override fun createProduct(product: Product): Product? {
        return productRepository.saveAndFlush(product);
    }
}