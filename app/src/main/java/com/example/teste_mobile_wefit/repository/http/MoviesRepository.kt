package com.example.teste_mobile_wefit.repository.http

import com.example.teste_mobile_wefit.model.http.MoviesModel
import com.example.teste_mobile_wefit.repository.BaseRepository
import com.example.teste_mobile_wefit.service.api.RetrofitClient
import com.example.teste_mobile_wefit.service.api.service.MoviesService
import com.example.teste_mobile_wefit.service.listener.BaseListener
import kotlinx.coroutines.Job

class MoviesRepository : BaseRepository() {
    private val service = RetrofitClient().createService(MoviesService::class.java)

    fun getMovies(listiners: BaseListener<MoviesModel>): Job {
        return executeCoroutineHttp(
            call = { service.getMovies() },
            listeners = listiners
        )
    }
}