package com.r3d1r4ph.avitotestweather.presentation.weather

import androidx.lifecycle.viewModelScope
import com.r3d1r4ph.avitotestweather.presentation.common.ui.StatefulAppViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherAction
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherState
import com.r3d1r4ph.truedomain.cities.CheckIsCitySelectedUseCase
import com.r3d1r4ph.truedomain.cities.FetchCityByLocationUseCase
import com.r3d1r4ph.truedomain.common.model.Location
import kotlinx.coroutines.launch

class WeatherViewModel(
	private val checkIsCitySelectedUseCase: CheckIsCitySelectedUseCase,
	private val fetchCityByLocationUseCase: FetchCityByLocationUseCase
) : StatefulAppViewModel<WeatherState, WeatherAction>(
	defaultState = WeatherState(loading = true)
) {

	init {
		checkIsCitySelected()
	}

	fun isLoading(): Boolean = _state.value?.loading ?: true

	private fun fetchCityByLocation(latitude: Float, longitude: Float) {
		viewModelScope.launch {
			fetchCityByLocationUseCase(Location(latitude = latitude, longitude = longitude))
				.onSuccess {
					_event.postEvent(WeatherAction.OpenDashboardScreen)
					updateState { copy(loading = false) }
				}
				.onFailure {
					openSearchCityScreen()
				}
		}
	}

	fun onLocationPermissionGranted() {
		_event.postEvent(WeatherAction.CheckCurrentLocation)
	}

	fun onLocationPermissionDenied() {
		openSearchCityScreen()
	}

	fun onLocationGet(location: android.location.Location?) {
		if (location != null) {
			fetchCityByLocation(latitude = location.latitude.toFloat(), location.longitude.toFloat())
		} else {
			openSearchCityScreen()
		}
	}

	private fun openSearchCityScreen() {
		_event.postEvent(WeatherAction.OpenSearchCityScreen)
		updateState { copy(loading = false) }
	}

	private fun checkIsCitySelected() {
		viewModelScope.launch {
			checkIsCitySelectedUseCase(Unit)
				.onSuccess { selected ->
					if (selected) {
						_event.postEvent(WeatherAction.OpenDashboardScreen)
						updateState { copy(loading = false) }
					} else {
						_event.postEvent(WeatherAction.CheckCurrentLocation)
					}
				}
				.onFailure {
					throw it
				}
		}
	}
}