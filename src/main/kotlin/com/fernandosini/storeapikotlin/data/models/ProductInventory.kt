package com.fernandosini.storeapikotlin.data.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "product_inventory")
class ProductInventory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "inventory_id") var id: Long = -1,
   // @OneToOne(mappedBy = "inventory") var product: Product? = null,
    @Column(name = "quantity") var quantity: Long = -1,
    @Column(name = "created_at") var createdAt: ZonedDateTime,
    @Column(name = "modified_at") var modifiedAt: ZonedDateTime? = null,
    @Column(name = "deleted_at") var deletedAt: ZonedDateTime? = null
) : Serializable {

}