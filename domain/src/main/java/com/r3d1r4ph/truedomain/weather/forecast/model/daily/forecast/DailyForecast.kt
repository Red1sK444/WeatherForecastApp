package com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast

import com.r3d1r4ph.truedomain.weather.forecast.model.Weather

data class DailyForecast(
	val dateTime: Long,
	val sunrise: Long,
	val sunset: Long,
	val temperature: Temperature,
	val feelsLike: FeelsLike,
	val pressure: Int,
	val humidity: Int,
	val weather: Weather,
	val speed: Float
)
