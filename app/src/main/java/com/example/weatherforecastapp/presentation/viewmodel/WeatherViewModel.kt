package com.example.weatherforecastapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.presentation.model.UIState
import com.example.weatherforecastapp.data.model.WeatherResponseModel
import com.example.weatherforecastapp.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _weatherFlow = MutableStateFlow<UIState<WeatherResponseModel>>(UIState.InitialState())
    val weatherFlow: StateFlow<UIState<WeatherResponseModel>> = _weatherFlow

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            _weatherFlow.value = UIState.Loading()
            apiRepository.getWeatherForecast(city)
                .catch { exception ->
                    run {
                        _weatherFlow.value = UIState.Error(exception.message)
                    }
                }
                .collect {
                    _weatherFlow.value = UIState.Success(it)
                }
        }
    }

}