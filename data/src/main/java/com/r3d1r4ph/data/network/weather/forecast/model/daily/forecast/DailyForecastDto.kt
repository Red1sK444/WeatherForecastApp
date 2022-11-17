package com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast

import com.r3d1r4ph.data.network.weather.forecast.model.WeatherDto
import com.r3d1r4ph.data.network.weather.forecast.model.toDomain
import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.DailyForecast
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DailyForecastDto(
	@SerialName("dt")
	val dateTime: Long,
	val sunrise: Long,
	val sunset: Long,
	@SerialName("temp")
	val temperature: TemperatureDto,
	@SerialName("feels_like")
	val feelsLike: FeelsLikeDto,
	val pressure: Int,
	val humidity: Int,
	val weather: List<WeatherDto>,
	val speed: Float
)

internal fun DailyForecastDto.toDomain(): DailyForecast =
	DailyForecast(
		dateTime = dateTime,
		sunrise = sunrise,
		sunset = sunset,
		temperature = temperature.toDomain(),
		feelsLike = feelsLike.toDomain(),
		pressure = pressure,
		humidity = humidity,
		weather = weather.first().toDomain(),
		speed = speed
	)
