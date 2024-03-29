package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
}