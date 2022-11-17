package com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast

data class Temperature(
	val day: Float,
	val min: Float,
	val max: Float,
	val night: Float,
	val evening: Float,
	val morning: Float,
)
