package com.example.teste_mobile_wefit.repository.local

import android.content.Context
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.repository.BaseRepository
import com.example.teste_mobile_wefit.service.listener.BaseListener
import com.example.teste_mobile_wefit.service.local.AppDataBase
import kotlinx.coroutines.Job

class CartRepository(context: Context) : BaseRepository() {
    private val database = AppDataBase.getDataBase(context).cartDAO()

    fun createCart(data: CartEntity, listeners: BaseListener<Long>): Job {
        return executeCoroutineDB(
            dbCall = { database.createCart(data) },
            listeners = listeners
        )
    }

    fun getLastOpenCart(listeners: BaseListener<Long?>): Job {
        return executeCoroutineDB(
            dbCall = { database.getLastOpenCart() },
            listeners = listeners
        )
    }

    fun getCart(listeners: BaseListener<CartEntity?>): Job {
        return executeCoroutineDB(
            dbCall = { database.getCart() },
            listeners = listeners
        )
    }

    fun getItemsCartOpen(cartId: Long, listeners: BaseListener<List<CartItemEntity>?>): Job {
        return executeCoroutineDB(
            dbCall = { database.getItemsCartOpen(cartId) },
            listeners = listeners
        )
    }

    fun updateTotalCart(cartId: Long, listeners: BaseListener<Int>): Job {
        return executeCoroutineDB(
            dbCall = { database.updateTotalCart(cartId) },
            listeners = listeners
        )
    }

    fun updateQuantityItem(newQuantity: Int, cartItemId: Int, cartId: Long, listeners: BaseListener<Int>): Job {
        return executeCoroutineDB(
            dbCall = { database.updateQuantityItem(newQuantity, cartItemId, cartId) },
            listeners = listeners
        )
    }

    fun addItemCart(data: CartItemEntity, listeners: BaseListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.addItemToCart(data) },
            listeners = listeners
        )
    }

    fun deleteItem(data: CartItemEntity, listeners: BaseListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.deleteItem(data) },
            listeners = listeners
        )
    }

    fun finishCart(cartId: Int, dateFinalized: String, listeners: BaseListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.finishCart(cartId, dateFinalized) },
            listeners = listeners
        )
    }
}
