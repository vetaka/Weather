package com.example.weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherListBinding
import com.example.weather.domain.Weather
import com.example.weather.view.details.DetailsFragment
import com.example.weather.view.details.OnItemClick
import com.example.weather.viewmodel.MainActivity
import com.example.weather.viewmodel.WeatherListAdapter
import com.example.weather.viewmodel.WeatherListVewModel

class WeatherListFragment : Fragment(), OnItemClick {
    companion object {
        fun newInstance() = WeatherListFragment()
    }

    var isRussian = true

    // lateinit var binding: FragmentWeatherListBinding
    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    lateinit var viewModel: WeatherListVewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListVewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)

            }

        })
        //viewModel.sentRequest()
        binding.weatherListFragmentFAB.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                viewModel.getWeatherListForRussia()
                binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getWeatherListForWorld()
                binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_earth)
            }
        }
        viewModel.getWeatherListForRussia()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {/*TO DO*/
            }
            AppState.Loading -> {/*TO DO*/
            }
            is AppState.SuccessOne -> {
                val result = appState.weatherData
            }

            is AppState.SuccessMulti -> {
                binding.mainFragmentRecyclerView.adapter = WeatherListAdapter(appState.weatherList,this)
//                binding.cityName.text = result.city.name
//                binding.temperatureValue.text = result.temperature.toString()
//                binding.feelsLikeValue.text = result.feelsLike.toString()
//                binding.cityCoordinates.text = "${result.city.lat} / ${result.city.lon}"
                //Toast.makeText(requireContext(),"РАБОТАЕТ $result", Toast.LENGTH_LONG).show()

            }
        }

    }
    override fun onItemClick(weather: Weather){
        (binding.root.context as MainActivity).supportFragmentManager.beginTransaction().replace(
            R.id.container, DetailsFragment.newInstance(weather)
        ).commit()

    }
}
