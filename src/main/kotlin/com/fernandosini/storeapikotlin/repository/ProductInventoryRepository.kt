package com.fernandosini.storeapikotlin.repository

import com.fernandosini.storeapikotlin.data.models.ProductInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductInventoryRepository : JpaRepository<ProductInventory, Long> {
   // @Modifying
  //  @Query("update product_inventory inv set inv.quantity=:quantity where inv.product.id =:productId")
   // fun findProductInventoryAndUpdateQuantity(@Param("productId") productId: Long, @Param("quantity") quantity: Long): Unit {

    //}
}