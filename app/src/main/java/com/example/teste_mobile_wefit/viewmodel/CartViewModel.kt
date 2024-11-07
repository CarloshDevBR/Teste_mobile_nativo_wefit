package com.example.teste_mobile_wefit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.repository.local.CartRepository
import com.example.teste_mobile_wefit.service.listener.BaseListener
import kotlinx.coroutines.Job

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartRepoDB = CartRepository(application.applicationContext)

    private var job: Job? = null

    fun deleteItem(item: CartItemEntity) {
        job = cartRepoDB.deleteItem(
            data = item,
            listeners = object : BaseListener<Unit> {
                override fun onSuccess(response: Unit) {
                    updateTotalCart(item.cartId)
                }

                override fun onError(message: String) {}

                override fun onLoading() {}
            }
        )
    }

    fun updateQuantity(cartItem: CartItemEntity, newQuantity: Int) {
        job = cartRepoDB.updateQuantityItem(
            newQuantity = newQuantity,
            cartItemId = cartItem.id,
            cartId = cartItem.cartId,
            listeners = object : BaseListener<Int> {
                override fun onSuccess(response: Int) {
                    updateTotalCart(cartItem.cartId)
                }

                override fun onError(message: String) {}

                override fun onLoading() {}
            })
    }

    fun finishCart(cartId: Int, dateFinalized: String, onFinished: () -> Unit) {
        job = cartRepoDB.finishCart(
            cartId = cartId,
            dateFinalized = dateFinalized,
            listeners = object : BaseListener<Unit> {
                override fun onSuccess(response: Unit) {
                    onFinished()
                }

                override fun onError(message: String) {}

                override fun onLoading() {}
            }
        )
    }

    private fun updateTotalCart(cartId: Long) {
        job = cartRepoDB.updateTotalCart(
            cartId = cartId,
            listeners = object : BaseListener<Int> {
                override fun onSuccess(response: Int) {}

                override fun onError(message: String) {}

                override fun onLoading() {}
            })
    }
}