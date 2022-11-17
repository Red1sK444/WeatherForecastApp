package com.r3d1r4ph.domain.weather.forecast.model

data class MainInfo(
	val temperature: Float,
	val feelsLike: Float,
	val minTemperature: Float,
	val maxTemperature: Float,
	val pressure: Float,
	val humidity: Int
)