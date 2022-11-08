package com.example.weather.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryLocalImpl
import com.example.weather.model.RepositoryRemoteImpl
import com.example.weather.viewmodel.AppState

class WeatherListVewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    ) : ViewModel() {

    lateinit var repository: Repository

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
    }

    fun sentRequest() {
        liveData.value = AppState.Loading // пошла загрузка
        if ((0..3).random() == 1) {
            liveData.postValue(AppState.Error(throw IllegalStateException("что-то пошло не так")))
        } else
            liveData.postValue(
                AppState.Success(
                    repository.getWeather(
                        55.755826,
                        37.617299900000035
                    )
                )
            )
    }

    private fun isConnection(): Boolean {
        return false

    }

    override fun onCleared() { //HW
        super.onCleared()
    }

}