package com.example.teste_mobile_wefit.repository

import com.example.teste_mobile_wefit.model.http.MoviesModel
import com.example.teste_mobile_wefit.service.api.RetrofitClient
import com.example.teste_mobile_wefit.service.api.service.MoviesService
import com.example.teste_mobile_wefit.service.listener.APIListener
import kotlinx.coroutines.Job

class MoviesRepository : BaseRepository() {
    private val service = RetrofitClient().createService(MoviesService::class.java)

    fun getMovies(listiners: APIListener<MoviesModel>): Job {
        return executeCoroutine(
            call = { service.getMovies() },
            listeners = listiners
        )
    }
}