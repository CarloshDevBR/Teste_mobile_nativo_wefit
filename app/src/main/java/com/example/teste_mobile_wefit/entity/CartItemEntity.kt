package com.example.teste_mobile_wefit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(
    @PrimaryKey
    val id: Int,
    val cartId: Long,
    val name: String,
    val image: String,
    val quantity: Int,
    val price: Double,
    val subtotal: Double,
    val date: String
)