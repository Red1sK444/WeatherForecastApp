package com.r3d1r4ph.avitotestweather.presentation.weather.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.Action

sealed class WeatherAction : Action {
	object OpenSearchCityScreen : WeatherAction()
	object OpenDashboardScreen : WeatherAction()
	object CheckCurrentLocation : WeatherAction()
}
