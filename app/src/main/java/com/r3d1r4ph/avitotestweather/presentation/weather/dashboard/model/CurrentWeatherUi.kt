package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils.formatByPattern
import com.r3d1r4ph.truedomain.weather.forecast.model.current.weather.CurrentWeather

data class CurrentWeatherUi(
	val city: String,
	val country: String,
	val formattedDateTime: String,
	val temperature: Int,
	val description: String,
	val minTemperature: Int,
	val maxTemperature: Int,
	val humidity: Int,
	val feelsLikeTemperature: Int
)

internal fun CurrentWeather.toUi(): CurrentWeatherUi =
	CurrentWeatherUi(
		city = city,
		country = country,
		formattedDateTime = dateTime.formatByPattern(),
		temperature = main.temperature.toInt(),
		description = weather.description.replaceFirstChar { it.uppercaseChar() },
		minTemperature = main.minTemperature.toInt(),
		maxTemperature = main.maxTemperature.toInt(),
		humidity = main.humidity,
		feelsLikeTemperature = main.feelsLike.toInt()
	)
