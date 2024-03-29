package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.OrderItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : JpaRepository<OrderItems, Long> {
}