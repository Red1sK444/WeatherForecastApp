package com.r3d1r4ph.data.network.weather.forecast.model

import com.r3d1r4ph.truedomain.weather.forecast.model.MainInfo
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MainInfoDto(
	@SerialName("temp")
	val temperature: Float,
	@SerialName("feels_like")
	val feelsLike: Float,
	@SerialName("temp_min")
	val minTemperature: Float,
	@SerialName("temp_max")
	val maxTemperature: Float,
	val pressure: Float,
	val humidity: Int
)

internal fun MainInfoDto.toDomain(): MainInfo =
	MainInfo(
		temperature = temperature,
		feelsLike = feelsLike,
		minTemperature = minTemperature,
		maxTemperature = maxTemperature,
		pressure = pressure,
		humidity = humidity
	)