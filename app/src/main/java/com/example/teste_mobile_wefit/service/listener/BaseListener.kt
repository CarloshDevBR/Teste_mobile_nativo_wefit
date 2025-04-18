package com.example.teste_mobile_wefit.service.listener

interface BaseListener<T> {
    fun onSuccess(response: T)

    fun onError(message: String)

    fun onLoading()
}