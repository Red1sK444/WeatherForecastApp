package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.r3d1r4ph.avitotestweather.presentation.common.ui.AppViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardAction
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardState
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.toUi
import com.r3d1r4ph.domain.cities.FetchCityByLocationUseCase
import com.r3d1r4ph.domain.common.model.Location
import com.r3d1r4ph.domain.device.location.GetDeviceLocationUseCase
import com.r3d1r4ph.domain.weather.forecast.FetchWeatherForecastUseCase
import com.r3d1r4ph.domain.weather.forecast.ObserveWeatherForecastUseCase
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DashboardViewModel(
	private val fetchWeatherForecastUseCase: FetchWeatherForecastUseCase,
	private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
	private val fetchCityByLocationUseCase: FetchCityByLocationUseCase,
	observeWeatherForecastUseCase: ObserveWeatherForecastUseCase,
) : AppViewModel<DashboardAction>() {

	val state: LiveData<DashboardState> = observeWeatherForecastUseCase(Unit)
		.mapNotNull { result ->
			val dashboardState = result.getOrNull()?.let { weatherForecast ->
				DashboardState(
					currentWeatherUi = weatherForecast.currentWeather?.toUi(),
					hourlyForecastUis = weatherForecast.hourlyForecasts.map { it.toUi() },
					dailyForecastUis = weatherForecast.dailyForecasts.map { it.toUi() }
				)
			}

			if (dashboardState == null) {
				_event.postEvent(DashboardAction.ShowError)
			}
			dashboardState
		}
		.onStart {
			emit(DashboardState(currentWeatherUi = null, hourlyForecastUis = emptyList(), dailyForecastUis = emptyList()))
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

	fun onMyLocationClick() {
		_event.postEvent(DashboardAction.RequestLocationPermission)
	}

	fun onRequestLocationPermissionResult(isGranted: Boolean) {
		if (isGranted) {
			fetchDeviceLocationData()
		} else {
			_event.postEvent(DashboardAction.ShowError)
		}
	}

	private fun fetchDeviceLocationData() {
		viewModelScope.launch {
			getDeviceLocationUseCase(Unit)
				.onSuccess { fetchCityByLocation(it) }
				.onFailure { _event.postEvent(DashboardAction.ShowError) }
		}
	}

	private suspend fun fetchCityByLocation(location: Location) {
		fetchCityByLocationUseCase(location)
			.onSuccess { fetchWeatherForecast() }
			.onFailure { _event.postEvent(DashboardAction.ShowError) }
	}

	fun onSearchCityClick() {
		_event.postEvent(DashboardAction.OpenSearchCityScreen)
	}
}