package com.fernandosini.storeapikotlin.services

import com.fernandosini.storeapikotlin.exceptions.NotFoundException
import com.fernandosini.storeapikotlin.repository.ProductInventoryRepository
import org.springframework.stereotype.Service

@Service
class ProductInventoryService(private val inventoryRepository: ProductInventoryRepository) {


    fun updateById(id: Long): Unit {

        inventoryRepository.findById(id).orElseThrow{NotFoundException("")}

    }
}