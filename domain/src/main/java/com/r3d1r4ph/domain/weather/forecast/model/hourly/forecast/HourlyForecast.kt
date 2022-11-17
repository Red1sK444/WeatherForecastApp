package com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast

import com.r3d1r4ph.domain.weather.forecast.model.MainInfo
import com.r3d1r4ph.domain.weather.forecast.model.Weather

data class HourlyForecast(
	val dateTime: Long,
	val main: MainInfo,
	val weather: Weather,
	val visibility: Int,
	val precipitationProbability: Float,
	val formattedDate: String
)
