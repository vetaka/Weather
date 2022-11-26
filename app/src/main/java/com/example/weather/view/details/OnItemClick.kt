package com.example.weather.view.details

import com.example.weather.domain.Weather

interface OnItemClick {
    fun onItemClick(weather:Weather)
}