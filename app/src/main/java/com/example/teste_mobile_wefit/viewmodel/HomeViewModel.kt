package com.example.teste_mobile_wefit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.model.http.MoviesModel
import com.example.teste_mobile_wefit.model.http.ProductModel
import com.example.teste_mobile_wefit.repository.http.MoviesRepository
import com.example.teste_mobile_wefit.repository.local.CartRepository
import com.example.teste_mobile_wefit.service.api.NetworkResponse
import com.example.teste_mobile_wefit.service.listener.APIListener
import com.example.teste_mobile_wefit.service.listener.DBListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepo = MoviesRepository()

    private val cartRepoDB = CartRepository(application.applicationContext)

    private val _movies = MutableStateFlow<NetworkResponse<MoviesModel>>(NetworkResponse.Initial)
    val movies: StateFlow<NetworkResponse<MoviesModel>> get() = _movies

    private var job: Job? = null

    fun getMovies() {
        job = moviesRepo.getMovies(listiners = object : APIListener<MoviesModel> {
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

    fun addItemCart(data: ProductModel) {
        cartRepoDB.addItemCart(
            data = CartItemEntity(
                name = data.title,
                price = data.price,
                quantity = 1,
                cartId = 0,
                image = data.image,
                date = "14/06/2024",
            ),
            listeners = object : DBListener<Unit> {
                override fun onSuccess(response: Unit?) {

                }

                override fun onError(message: String) {

                }

                override fun onLoading() {

                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
    }
}