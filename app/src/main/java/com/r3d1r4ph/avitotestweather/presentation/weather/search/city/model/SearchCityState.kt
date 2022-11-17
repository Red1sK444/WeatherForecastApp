package com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.State
import com.r3d1r4ph.truedomain.cities.model.City

data class SearchCityState(
	val cities: List<City>,
	val loading: Boolean,
) : State
