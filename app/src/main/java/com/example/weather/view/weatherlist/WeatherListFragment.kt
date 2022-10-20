package com.example.weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.databinding.FragmentWeatherListBinding
import com.example.weather.view.viewmodel.AppState

class WeatherListFragment : Fragment() {
    companion object {
        fun newInstance() = WeatherListFragment()
    }

    lateinit var binding: FragmentWeatherListBinding
    lateinit var viewModel: WeatherListVewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListVewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)

            }

        })
        viewModel.sentRequest()
    }
    private fun renderData(appState: AppState){
        when (appState){
            is AppState.Error -> {/*TO DO*/}
            AppState.Loading -> {/*TO DO*/}
            is AppState.Success -> {
                val result = appState.weatherData
                Toast.makeText(requireContext(),"РАБОТАЕТ $result", Toast.LENGTH_LONG).show()

            }
        }

    }
}
