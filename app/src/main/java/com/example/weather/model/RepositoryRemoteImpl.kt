package com.example.weather.model

import com.example.weather.domain.Weather

class RepositoryRemoteImpl : Repository {
    override fun getListWeather(): List<Weather> {
        Thread {    // запрос в репозиторий
            Thread.sleep(200L)
        }.start()
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread {    // запрос в репозиторий
            Thread.sleep(300L)
        }.start()
        return Weather()
    }
}