package com.example.apparchitecturefirstexercise.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private const val BASE_URL = "https://covid-193.p.rapidapi.com"

        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            val authorizationInterceptor = AuthorizationInterceptor()
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(authorizationInterceptor)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}