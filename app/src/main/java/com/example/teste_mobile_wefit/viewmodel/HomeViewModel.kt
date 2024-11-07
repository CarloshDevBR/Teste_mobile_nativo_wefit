package com.example.teste_mobile_wefit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.model.http.MoviesModel
import com.example.teste_mobile_wefit.model.http.ProductModel
import com.example.teste_mobile_wefit.repository.http.MoviesRepository
import com.example.teste_mobile_wefit.repository.local.CartRepository
import com.example.teste_mobile_wefit.service.api.NetworkResponse
import com.example.teste_mobile_wefit.service.listener.BaseListener
import com.example.teste_mobile_wefit.utils.AppDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepo = MoviesRepository()

    private val cartRepoDB = CartRepository(application.applicationContext)

    private val _movies = MutableStateFlow<NetworkResponse<MoviesModel>>(NetworkResponse.Initial)
    val movies: StateFlow<NetworkResponse<MoviesModel>> get() = _movies

    private var job: Job? = null

    fun getMovies() {
        job = moviesRepo.getMovies(listiners = object : BaseListener<MoviesModel> {
            override fun onSuccess(response: MoviesModel) {
                _movies.update { NetworkResponse.Success(response) }
            }

            override fun onError(message: String) {
                _movies.update { NetworkResponse.Failure(message.ifBlank { "Error desconhecido" }) }

            }

            override fun onLoading() {
                _movies.update { NetworkResponse.Loading }
            }
        })
    }

    fun addItemCart(data: ProductModel, quantity: Int, callBack: () -> Unit) {
        viewModelScope.launch {
            val dateToday = AppDate().getDateToday()

            val (cartId, isOpen) = getLastOpenCart()

            var item: CartItemEntity? = null

            val newQuantity = quantity + 1

            if (isOpen) {
                item = CartItemEntity(
                    id = data.id,
                    name = data.title,
                    price = data.price,
                    quantity = newQuantity,
                    cartId = cartId,
                    image = data.image,
                    date = dateToday,
                    subtotal = data.price
                )
            } else {
                val createdCartId = createCart()

                createdCartId?.let {
                    item = CartItemEntity(
                        id = data.id,
                        name = data.title,
                        price = data.price,
                        quantity = newQuantity,
                        cartId = createdCartId,
                        image = data.image,
                        date = dateToday,
                        subtotal = data.price
                    )
                }
            }

            updateTotalCart(cartId)

            item?.let {
                job = cartRepoDB.addItemCart(
                    data = it,
                    listeners = object : BaseListener<Unit> {
                        override fun onSuccess(response: Unit) {

                        }

                        override fun onError(message: String) {

                        }

                        override fun onLoading() {

                        }
                    })
            }

            callBack()
        }
    }

    private suspend fun updateTotalCart(cartId: Long): Int? = suspendCancellableCoroutine { continuation ->
            job = cartRepoDB.updateTotalCart(
                cartId = cartId,
                listeners = object : BaseListener<Int> {
                    override fun onSuccess(response: Int) {
                        continuation.resume(response) {}
                    }

                    override fun onError(message: String) {
                        continuation.resume(null) {}
                    }

                    override fun onLoading() {}
                })
        }

    private suspend fun getLastOpenCart(): Pair<Long, Boolean> = suspendCancellableCoroutine { continuation ->
            job = cartRepoDB.getLastOpenCart(
                listeners = object : BaseListener<Long?> {
                    override fun onSuccess(response: Long?) {
                        val cartId = response ?: 0
                        val isOpenCart = cartId > 0

                        continuation.resume(Pair(cartId, isOpenCart)) {}
                    }

                    override fun onError(message: String) {
                        continuation.resume(Pair(0, false)) {}
                    }

                    override fun onLoading() {}
                }
            )
        }

    private suspend fun createCart(): Long? = suspendCancellableCoroutine { continuation ->
        job = cartRepoDB.createCart(
            data = CartEntity(
                finalized = false,
                total = 0.0,
                dateFinalized = ""
            ),
            listeners = object : BaseListener<Long> {
                override fun onSuccess(response: Long) {
                    continuation.resume(response) {}
                }

                override fun onError(message: String) {
                    continuation.resume(null) {}
                }

                override fun onLoading() {}
            }
        )
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
    }
}