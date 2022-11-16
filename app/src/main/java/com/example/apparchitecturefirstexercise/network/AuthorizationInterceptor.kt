package com.example.apparchitecturefirstexercise.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder().addHeader(
            "X-RapidAPI-Key",
            "1a14206c5dmshf0ab2a7745daaacp15e52cjsnf6ea3f562f60",
        ).build()
        return chain.proceed(newRequest)
    }
}