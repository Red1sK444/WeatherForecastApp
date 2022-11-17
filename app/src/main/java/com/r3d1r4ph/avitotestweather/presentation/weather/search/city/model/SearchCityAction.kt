package com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.Action
import com.r3d1r4ph.truedomain.cities.model.City

sealed class SearchCityAction : Action {
	data class SelectCity(val city: City) : SearchCityAction()
	object OpenDashboardScreen : SearchCityAction()
	object ShowError : SearchCityAction()
}
