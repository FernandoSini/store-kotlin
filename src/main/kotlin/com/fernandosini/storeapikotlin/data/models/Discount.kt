package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*


@Entity(name = "discount")
class Discount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "discount_id") var id: Long = -1,
    @OneToMany(mappedBy = "discount") var products: MutableSet<Product>
) {
}