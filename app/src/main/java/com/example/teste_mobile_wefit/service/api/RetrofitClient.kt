package com.example.teste_mobile_wefit.service.api

import com.example.teste_mobile_wefit.constants.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val BASE_URL = AppConstants.BASE_URL

    private fun getRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()

         return Retrofit
             .Builder()
             .baseUrl(BASE_URL)
             .client(okHttpClient.build())
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }

    fun <S> createService(service: Class<S>): S = getRetrofitInstance().create(service)
}