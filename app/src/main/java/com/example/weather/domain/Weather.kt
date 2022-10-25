package com.example.weather.domain

data class Weather (
    val city: City = getDefaultCity(),
    val temperature: Int = 10,
    val feelsLike: Int = 10
    )
data class City(
    val name: String,
    val lat: Double,
    val lon: Double
)
    fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)
