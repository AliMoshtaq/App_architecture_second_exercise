package com.example.apparchitecturefirstexercise.models

data class Cases(
    val active: Int,
    val critical: Int,
    val new: Int,
    val recovered: Int,
    val total: Int
)