package com.example.apparchitecturefirstexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apparchitecturefirstexercise.network.ApiService
import com.example.apparchitecturefirstexercise.network.AuthorizationInterceptor
import com.example.apparchitecturefirstexercise.models.CovidData
import com.example.apparchitecturefirstexercise.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val retroInstance = RetrofitInstance.getRetroInstance()

    private val apiService = retroInstance.create(ApiService::class.java)

    private val data = MutableLiveData<CovidData>()
    val dataList: LiveData<CovidData>
        get() = data

    private var error = MutableLiveData<String>()
    val errorList: LiveData<String>
        get() = error

    fun retrieveData(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                data.value = apiService.getCovidDetails().body()
            } catch (e: Exception){
                error.value = e.localizedMessage
            }
        }
    }
}
