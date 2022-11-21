package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.*
import com.example.weather.view.weatherlist.AppState

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

    fun getWeatherListForRussia(){
        sentRequest(Location.Russian)
    }

    fun getWeatherListForWorld(){
        sentRequest(Location.World)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading // пошла загрузка
        if ((0..3).random() == 1) {
            liveData.postValue(AppState.Error(throw IllegalStateException("что-то пошло не так")))
        } else
            liveData.postValue(
                AppState.SuccessMulti(
                    repositoryMulti.getListWeather(location)))
    }

    private fun isConnection(): Boolean {
        return false

    }

    override fun onCleared() { //HW
        super.onCleared()
    }

}