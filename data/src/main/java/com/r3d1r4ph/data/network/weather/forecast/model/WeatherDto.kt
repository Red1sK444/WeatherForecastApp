package com.r3d1r4ph.data.network.weather.forecast.model

import com.r3d1r4ph.domain.weather.forecast.model.Weather

@kotlinx.serialization.Serializable
data class WeatherDto(
	val id: Int,
	val main: String,
	val description: String,
	val icon: String
)

internal fun WeatherDto.toDomain(): Weather =
	Weather(
		id = id,
		main = main,
		description = description,
		icon = "http://openweathermap.org/img/wn/$icon@4x.png"
	)
