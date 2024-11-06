package com.example.teste_mobile_wefit.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CartWithItemsRelationalEntity(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cartId"
    )
    val items: List<CartItemEntity>
)