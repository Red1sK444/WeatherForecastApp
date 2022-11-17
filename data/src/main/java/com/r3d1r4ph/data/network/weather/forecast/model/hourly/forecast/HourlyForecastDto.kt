package com.r3d1r4ph.data.network.weather.forecast.model.hourly.forecast

import com.r3d1r4ph.data.network.weather.forecast.model.MainInfoDto
import com.r3d1r4ph.data.network.weather.forecast.model.WeatherDto
import com.r3d1r4ph.data.network.weather.forecast.model.toDomain
import com.r3d1r4ph.truedomain.weather.forecast.model.hourly.forecast.HourlyForecast
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class HourlyForecastDto(
	@SerialName("dt")
	val dateTime: Long,
	val main: MainInfoDto,
	val weather: List<WeatherDto>,
	val visibility: Int,
	@SerialName("pop")
	val precipitationProbability: Float,
	@SerialName("dt_txt")
	val formattedDate: String
)

internal fun HourlyForecastDto.toDomain(): HourlyForecast =
	HourlyForecast(
		dateTime = dateTime,
		main = main.toDomain(),
		weather = weather.first().toDomain(),
		visibility = visibility,
		precipitationProbability = precipitationProbability,
		formattedDate = formattedDate
	)
