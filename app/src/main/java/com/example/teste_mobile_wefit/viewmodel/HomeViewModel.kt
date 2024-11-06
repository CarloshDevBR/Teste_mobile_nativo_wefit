package com.example.teste_mobile_wefit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teste_mobile_wefit.model.http.MoviesModel
import com.example.teste_mobile_wefit.repository.MoviesRepository
import com.example.teste_mobile_wefit.service.api.NetworkResponse
import com.example.teste_mobile_wefit.service.listener.APIListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val repository = MoviesRepository()

    private val _movies = MutableStateFlow<NetworkResponse<MoviesModel>>(NetworkResponse.Initial)
    val movies: StateFlow<NetworkResponse<MoviesModel>> get() = _movies

    private var job: Job? = null

    fun getMovies() {
        job = repository.getMovies(listiners = object : APIListener<MoviesModel> {
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

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
    }
}