package com.example.weather.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentWeatherListBinding
import com.example.weather.databinding.FragmentWeatherListRecycleItemBinding
import com.example.weather.domain.Weather

class WeatherListAdapter(val dataList:List<Weather>): RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentWeatherListRecycleItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    class WeatherViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(weather:Weather){
            val binding = FragmentWeatherListRecycleItemBinding.bind(itemView)
            binding.cityName.text = weather.city.name
        }
    }
}