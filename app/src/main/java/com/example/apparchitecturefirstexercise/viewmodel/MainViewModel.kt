package com.example.apparchitecturefirstexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparchitecturefirstexercise.network.ApiService
import com.example.apparchitecturefirstexercise.models.CovidData
import com.example.apparchitecturefirstexercise.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val retroInstance = RetrofitInstance.getRetroInstance()

    private val apiService = retroInstance.create(ApiService::class.java)

    // Replaced with LiveData
    val data = MutableSharedFlow<CovidData?>()

    private var error = MutableLiveData<String>()
    val errorList: LiveData<String>
        get() = error

    fun retrieveData(){

        // Modified -> data.value with data.emit()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModelScope.launch{
                data.emit(apiService.getCovidDetails().body())
                }
            } catch (e: Exception){
                error.value = e.localizedMessage
            }
        }
    }
}
