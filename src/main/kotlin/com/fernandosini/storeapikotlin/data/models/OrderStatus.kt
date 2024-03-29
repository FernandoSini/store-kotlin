package com.fernandosini.storeapikotlin.data.models

enum class OrderStatus {
    Processing, WaitingPayment,
    Rejected, Finished, Dispatched,
    Delivered, Closed
}