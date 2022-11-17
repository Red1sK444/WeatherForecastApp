package com.r3d1r4ph.domain.weather.forecast.model.current.weather

import com.r3d1r4ph.domain.weather.forecast.model.MainInfo
import com.r3d1r4ph.domain.weather.forecast.model.Weather

data class CurrentWeather(
	val weather: Weather,
	val main: MainInfo,
	val visibility: Int,
	val wind: Wind,
	val clouds: Clouds,
	val dateTime: Long,
	val sunMovement: SunMovement,
	val timezone: Long,
	val id: Int,
	val city: String,
	val country: String
)