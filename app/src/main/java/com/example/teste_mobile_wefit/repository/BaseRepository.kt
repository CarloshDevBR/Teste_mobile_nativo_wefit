package com.example.teste_mobile_wefit.repository

import com.example.teste_mobile_wefit.service.listener.APIListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

open class BaseRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    protected fun <T> executeCoroutine(
        call: suspend () -> Response<T>,
        listeners: APIListener<T>
    ): Job {
        return coroutineScope.launch {
            listeners.onLoading()

            val response = call()

            if (response.isSuccessful) {
                response.body()?.let {
                    listeners.onSuccess(it)
                }
            } else {
                listeners.onLoading()

                listeners.onError(response.errorBody().toString())
            }
        }
    }
}