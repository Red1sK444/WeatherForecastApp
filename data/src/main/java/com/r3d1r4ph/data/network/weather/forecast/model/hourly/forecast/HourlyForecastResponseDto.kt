package com.r3d1r4ph.data.network.weather.forecast.model.hourly.forecast

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class HourlyForecastResponseDto(
	@SerialName("list")
	val hourlyForecasts: List<HourlyForecastDto>
)
