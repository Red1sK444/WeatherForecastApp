package com.r3d1r4ph.avitotestweather.presentation.weather

import android.content.pm.PackageManager
import androidx.lifecycle.viewModelScope
import com.r3d1r4ph.avitotestweather.presentation.common.permissions.PermissionsManager
import com.r3d1r4ph.avitotestweather.presentation.common.ui.StatefulAppViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherAction
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherState
import com.r3d1r4ph.domain.cities.CheckIsCitySelectedUseCase
import com.r3d1r4ph.domain.cities.FetchCityByLocationUseCase
import com.r3d1r4ph.domain.common.model.Location
import com.r3d1r4ph.domain.device.location.GetDeviceLocationUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(
	private val checkIsCitySelectedUseCase: CheckIsCitySelectedUseCase,
	private val fetchCityByLocationUseCase: FetchCityByLocationUseCase,
	private val getDeviceLocationUseCase: GetDeviceLocationUseCase
) : StatefulAppViewModel<WeatherState, WeatherAction>(
	defaultState = WeatherState(loading = true)
) {

	init {
		checkIsCitySelected()
	}

	fun isLoading(): Boolean = _state.value?.loading ?: true

	fun onRequestPermissionResult(requestCode: Int, grantResults: IntArray) {
		if (requestCode == PermissionsManager.LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
			when (grantResults.first()) {
				PackageManager.PERMISSION_GRANTED -> onLocationPermissionGranted()
				PackageManager.PERMISSION_DENIED  -> openSearchCityScreen()
			}
		}
	}

	fun onLocationPermissionGranted() {
		viewModelScope.launch {
			getDeviceLocationUseCase(Unit)
				.onSuccess { fetchCityByLocation(it) }
				.onFailure { openSearchCityScreen() }
		}
	}

	private suspend fun fetchCityByLocation(location: Location) {
		fetchCityByLocationUseCase(location)
			.onSuccess {
				_event.postEvent(WeatherAction.OpenDashboardScreen)
				updateState { copy(loading = false) }
			}
			.onFailure {
				openSearchCityScreen()
			}
	}

	fun onLocationPermissionDenied() {
		_event.postEvent(WeatherAction.RequestLocationPermission)
	}

	private fun checkIsCitySelected() {
		viewModelScope.launch {
			checkIsCitySelectedUseCase(Unit)
				.onSuccess { selected ->
					if (selected) {
						_event.postEvent(WeatherAction.OpenDashboardScreen)
						updateState { copy(loading = false) }
					} else {
						_event.postEvent(WeatherAction.CheckLocationPermission)
					}
				}
				.onFailure {
					openSearchCityScreen()
				}
		}
	}

	private fun openSearchCityScreen() {
		_event.postEvent(WeatherAction.OpenSearchCityScreen)
		updateState { copy(loading = false) }
	}
}