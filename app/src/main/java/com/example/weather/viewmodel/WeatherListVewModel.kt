package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.*
import kotlin.random.Random

class WeatherListVewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {

    lateinit var repositoryMulti: RepositoryMany
    lateinit var repositoryOne: RepositoryOne

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repositoryOne = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
        repositoryMulti = RepositoryLocalImpl()
    }

    fun getWeatherListForRussia() {
        sentRequest(Location.Russian)
    }

    fun getWeatherListForWorld() {
        sentRequest(Location.World)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading // пошла загрузка
        // if ((0..3).random() == 1) {

        Thread {
            Thread.sleep(3000L)
            if ((0..3).random(Random(System.currentTimeMillis())) == 1) {
                liveData.postValue(AppState.Error(IllegalStateException("что-то пошло не так")))
            } else
                liveData.postValue(AppState.SuccessMulti(repositoryMulti.getListWeather(location)))
        }.start()   // запрос в репозиторий
    }

    private fun isConnection(): Boolean {
        return false

    }

    override fun onCleared() { //HW
        super.onCleared()
    }

}