package com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast

import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.Temperature
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TemperatureDto(
	val day: Float,
	val min: Float,
	val max: Float,
	val night: Float,
	@SerialName("eve")
	val evening: Float,
	@SerialName("morn")
	val morning: Float,
)

internal fun TemperatureDto.toDomain(): Temperature =
	Temperature(
		day = day,
		min = min,
		max = max,
		night = night,
		evening = evening,
		morning = morning
	)
