package com.example.apparchitecturefirstexercise.network

import com.example.apparchitecturefirstexercise.models.CovidData
import retrofit2.Call
import retrofit2.Response


import retrofit2.http.GET

interface ApiService {
    @GET("/statistics?country=italy")
    suspend fun getCovidDetails(): Response<CovidData>
}