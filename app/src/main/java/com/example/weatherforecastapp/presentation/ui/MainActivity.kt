package com.example.weatherforecastapp.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.presentation.ui.adapter.WeatherForecastAdapter
import com.example.weatherforecastapp.databinding.ActivityMainBinding
import com.example.weatherforecastapp.data.model.WeatherResponseModel
import com.example.weatherforecastapp.presentation.model.UIState
import com.example.weatherforecastapp.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var recyclerViewWeather: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        recyclerViewWeather = binding.recyclerview
        recyclerViewWeather.isNestedScrollingEnabled = false

        binding.btnSendRequest.setOnClickListener {
            // Text field validation
            if (binding.etCityName.text.isNullOrEmpty() or binding.etCityName.text.isNullOrBlank()) {
                binding.etCityName.error = "Field can't be empty"
            } else {
                // Get weather data
                weatherViewModel.getWeatherData(binding.etCityName.text.toString())

                val view: View? = this.currentFocus
                if (view != null) {
                    val inputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        observeFlowData()
    }

    private fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            weatherViewModel?.weatherFlow?.collect { state ->
                when (state) {
                    is UIState.InitialState -> {
                        hideProgress()
                    }
                    is UIState.Loading -> {
                        showProgress()
                    }
                    is UIState.Success -> {
                        hideProgress()
                        state.data?.let {
                            hideError()
                            updateUI(it)
                        } ?: showError()
                    }
                    is UIState.Error -> {
                        hideProgress()
                        showError()
                    }
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        binding.tvError.text = getString(R.string.error_message)
        binding.tvError.visibility = View.VISIBLE
        binding.recyclerview.visibility=View.GONE
        binding.currentWeatherLayout.visibility=View.GONE
    }
    private fun hideError(){
        binding.tvError.visibility = View.GONE
        binding.recyclerview.visibility=View.VISIBLE
        binding.currentWeatherLayout.visibility=View.VISIBLE
    }

    private fun updateUI(weatherData: WeatherResponseModel?) {
        weatherData?.location.let { location ->
            binding.tvCity.text ="Name: ${location?.name}"
        }

        weatherData?.current.let { current ->
            current?.condition.let { condition ->
                binding.tvCondition.text ="Condition: ${condition?.text}"
                setResultImage(condition?.icon)
            }
            binding.tvCelc.text ="Celcius: ${current?.tempC}"
            binding.tvHumid.text ="Humidity: ${current?.humidity}"
        }

        weatherData?.forecast.let { forecast->
            val newsAdapter = WeatherForecastAdapter(forecast?.forecastday!!)
            recyclerViewWeather.adapter = newsAdapter
            recyclerViewWeather.layoutManager = LinearLayoutManager(this)
            recyclerViewWeather.setHasFixedSize(true)
        }

    }

    private fun setResultImage(imageUrl: String?) {
        // Display image when image url is available
        imageUrl.let { url ->
            Glide.with(applicationContext)
                .load("https:$url")
                .into(binding.imageview)

            binding.imageview.visibility = View.VISIBLE
            return
        }

        // Hide image when image url is null
        binding.imageview.visibility = View.GONE
    }
}