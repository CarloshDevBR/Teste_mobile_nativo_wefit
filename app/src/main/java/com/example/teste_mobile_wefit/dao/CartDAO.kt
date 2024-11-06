package com.example.teste_mobile_wefit.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.entity.CartWithItemsRelationalEntity

@Dao
interface CartDAO {
    @Query("SELECT id FROM cart WHERE finalized = 0 ORDER BY id DESC LIMIT 1")
    suspend fun getLastOpenCart(): Int?

    @Query("SELECT * FROM cart WHERE finalized = 0 ORDER BY id DESC LIMIT 1")
    suspend fun getCart(): CartWithItemsRelationalEntity?

    @Insert
    suspend fun createCart(data: CartEntity): Unit

    @Query(
        """
            UPDATE 
                cart 
            SET 
                total = (
                    SELECT 
                        SUM(price * quantity) 
                    FROM 
                        cart_item 
                    WHERE 
                        cartId = :cartId)
            WHERE 
                id = :cartId
        """
    )
    suspend fun updateTotalCart(cartId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToCart(data: CartItemEntity)

    @Delete
    suspend fun removeItem(data: CartItemEntity)

    @Query("UPDATE cart SET finalized = 1, dateFinalized = :dateFinalized WHERE id = :cartId")
    suspend fun finishCart(cartId: Int, dateFinalized: String)
}
