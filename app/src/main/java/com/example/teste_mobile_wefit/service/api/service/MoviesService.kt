package com.example.teste_mobile_wefit.service.api.service

import com.example.teste_mobile_wefit.model.http.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("movies")
    suspend fun getMovies(): Response<MoviesModel>
}