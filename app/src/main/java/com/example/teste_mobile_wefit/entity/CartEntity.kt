package com.example.teste_mobile_wefit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val total: Double,
    val finalized: Boolean,
    val dateFinalized: String
)