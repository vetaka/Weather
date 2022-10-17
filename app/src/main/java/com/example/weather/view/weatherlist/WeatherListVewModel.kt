package com.example.weather.view.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.view.viewmodel.AppState
import java.lang.Thread.sleep

class WeatherListVewModel(val liveData:MutableLiveData<AppState> = MutableLiveData<AppState>()):ViewModel() {
    fun sentRequest(){
       liveData.value = AppState.Loading // пошла загрузка
       Thread{    // запрос в репозиторий
           sleep(2000L)
           liveData.postValue(AppState.Success(Any()))
       }.start()

    }

}