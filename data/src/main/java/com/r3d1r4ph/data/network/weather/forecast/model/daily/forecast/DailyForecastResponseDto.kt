package com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DailyForecastResponseDto(
	@SerialName("list")
	val dailyForecasts: List<DailyForecastDto>
)
