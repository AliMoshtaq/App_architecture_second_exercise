package com.example.apparchitecturefirstexercise.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.apparchitecturefirstexercise.R
import com.example.apparchitecturefirstexercise.databinding.ActivityMainBinding
import com.example.apparchitecturefirstexercise.models.CovidData
import com.example.apparchitecturefirstexercise.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeData()
        viewModel.retrieveData()
        setContentView(binding.root)
    }

    private fun observeData() {

        // Instead -> viewModel.data.observe using viewModel.data.collect inside coroutineScope
        lifecycleScope.launch {
            viewModel.data.collect{
                if (it != null) {
                    setCovidDetails(it)
                }
            }
        }

        viewModel.errorList.observe(this) {
            Snackbar.make(
                findViewById(R.id.main_view),
                "Error retrieving Data's",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Retry") { viewModel.retrieveData() }.show()
        }
    }

    private fun setCovidDetails(covidResult: CovidData) {
        binding.newCases.text =
            getString(R.string.new_cases, covidResult.response[0].cases.new)
        binding.activeCases.text =
            getString(R.string.active_cases, covidResult.response[0].cases.active)
        binding.criticalCases.text =
            getString(R.string.critical_cases, covidResult.response[0].cases.critical)
        binding.recovered.text =
            getString(R.string.recovered, covidResult.response[0].cases.recovered)
        binding.total.text =
            getString(R.string.total, covidResult.response[0].cases.total)
        binding.day.text =
            getString(R.string.day, covidResult.response[0].day)
    }
}

