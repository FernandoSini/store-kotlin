package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*

@Entity(name = "product_inventory")
class ProductInventory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "inventory_id") var id: Long = -1,
    @OneToMany(mappedBy ="inventory") var products: MutableSet<Product>
) {
}