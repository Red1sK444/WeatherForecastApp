package com.r3d1r4ph.domain.weather.forecast.model

data class Weather(
	val id: Int,
	val main: String,
	val description: String,
	val icon: String
)
