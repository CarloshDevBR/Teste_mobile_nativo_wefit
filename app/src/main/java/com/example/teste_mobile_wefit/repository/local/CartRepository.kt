package com.example.teste_mobile_wefit.repository.local

import android.content.Context
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.entity.CartWithItemsRelationalEntity
import com.example.teste_mobile_wefit.repository.BaseRepository
import com.example.teste_mobile_wefit.service.listener.DBListener
import com.example.teste_mobile_wefit.service.local.AppDataBase
import kotlinx.coroutines.Job

class CartRepository(context: Context) : BaseRepository() {
    private val database = AppDataBase.getDataBase(context).cartDAO()

    fun createCart(data: CartEntity, listeners: DBListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.createCart(data) },
            listeners = listeners
        )
    }

    fun getLastOpenCart(listeners: DBListener<Int?>): Job {
        return executeCoroutineDB(
            dbCall = { database.getLastOpenCart() },
            listeners = listeners
        )
    }

    fun getCart(listeners: DBListener<CartWithItemsRelationalEntity?>): Job {
        return executeCoroutineDB(
            dbCall = { database.getCart() },
            listeners = listeners
        )
    }

    fun updateTotalCart(cartId: Int, listeners: DBListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.updateTotalCart(cartId) },
            listeners = listeners
        )
    }

    fun addItemCart(data: CartItemEntity, listeners: DBListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.addItemToCart(data) },
            listeners = listeners
        )
    }

    fun removeItem(data: CartItemEntity, listeners: DBListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.removeItem(data) },
            listeners = listeners
        )
    }

    fun finishCart(cartId: Int, dateFinalized: String, listeners: DBListener<Unit>): Job {
        return executeCoroutineDB(
            dbCall = { database.finishCart(cartId, dateFinalized) },
            listeners = listeners
        )
    }
}
