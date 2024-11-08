package com.example.teste_mobile_wefit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.repository.local.CartRepository
import com.example.teste_mobile_wefit.service.listener.BaseListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val cartRepoDB = CartRepository(application.applicationContext)

    private val _cart = MutableStateFlow<CartEntity?>(null)
    val cart: StateFlow<CartEntity?> get() = _cart.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItemEntity>?>(null)
    val cartItems: StateFlow<List<CartItemEntity>?> get() = _cartItems.asStateFlow()

    private val _isVisibleBottomBar = MutableStateFlow(false)
    val isVisibleBottomBar: StateFlow<Boolean> get() = _isVisibleBottomBar.asStateFlow()

    fun setIsVisibleBottomBar(value: Boolean) {
        _isVisibleBottomBar.update { value }
    }

    fun attCartState() {
        viewModelScope.launch {
            val cartId = getLastOpenCart()

            _cartItems.update { getCartItems(cartId) }

            _cart.update { getCart() }
        }
    }

    private suspend fun getCart(): CartEntity? { return suspendCoroutine { continuation ->
            cartRepoDB.getCart(
                listeners = object : BaseListener<CartEntity?> {
                    override fun onSuccess(response: CartEntity?) {
                        continuation.resume(response)
                    }

                    override fun onError(message: String) {
                        continuation.resume(null)
                    }

                    override fun onLoading() {}
                }
            )
        }
    }

    private suspend fun getCartItems(cartId: Long): List<CartItemEntity>? { return suspendCoroutine { continuation ->
            cartRepoDB.getItemsCartOpen(
                cartId = cartId,
                listeners = object : BaseListener<List<CartItemEntity>?> {
                    override fun onSuccess(response: List<CartItemEntity>?) {
                        continuation.resume(response)
                    }

                    override fun onError(message: String) {
                        continuation.resume(null)
                    }

                    override fun onLoading() {}
                }
            )
        }
    }

    private suspend fun getLastOpenCart(): Long = suspendCoroutine { continuation ->
        cartRepoDB.getLastOpenCart(
            listeners = object : BaseListener<Long?> {
                override fun onSuccess(response: Long?) {
                    val cartId = response ?: 0
                    continuation.resume(cartId)
                }

                override fun onError(message: String) {
                    continuation.resume(0)
                }

                override fun onLoading() {}
            }
        )
    }
}
