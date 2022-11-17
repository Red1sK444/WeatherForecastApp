package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.r3d1r4ph.avitotestweather.presentation.common.ui.AppViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardAction
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardState
import com.r3d1r4ph.truedomain.weather.forecast.FetchWeatherForecastUseCase
import com.r3d1r4ph.truedomain.weather.forecast.ObserveWeatherForecastUseCase
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DashboardViewModel(
	private val fetchWeatherForecastUseCase: FetchWeatherForecastUseCase,
	observeWeatherForecastUseCase: ObserveWeatherForecastUseCase
) : AppViewModel<DashboardAction>() {

	val state: LiveData<DashboardState> = observeWeatherForecastUseCase(Unit)
		.mapNotNull { result ->
			val dashboardState = result.getOrNull()?.let { weatherForecast ->
				DashboardState(
					currentWeather = weatherForecast.currentWeather,
					hourlyForecasts = weatherForecast.hourlyForecasts,
					dailyForecasts = weatherForecast.dailyForecasts
				)
			}

			if (dashboardState == null) {
				_event.postEvent(DashboardAction.ShowError)
			}
			dashboardState
		}
		.onStart {
			emit(DashboardState(currentWeather = null, hourlyForecasts = emptyList(), dailyForecasts = emptyList()))
		}
		.asLiveData()

	fun fetchWeatherForecast() {
		viewModelScope.launch {
			fetchWeatherForecastUseCase(Unit)
				.onFailure {
					_event.postEvent(DashboardAction.ShowError)
				}
		}
	}

	fun onSearchCityClick() {
		_event.postEvent(DashboardAction.OpenSearchCityScreen)
	}
}