package com.r3d1r4ph.data.network.weather.forecast.model.current.weather

import com.r3d1r4ph.data.network.weather.forecast.model.MainInfoDto
import com.r3d1r4ph.data.network.weather.forecast.model.WeatherDto
import com.r3d1r4ph.data.network.weather.forecast.model.toDomain
import com.r3d1r4ph.domain.weather.forecast.model.current.weather.CurrentWeather
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CurrentWeatherDto(
	val weather: List<WeatherDto>,
	val main: MainInfoDto,
	val visibility: Int,
	val wind: WindDto,
	val clouds: CloudsDto,
	@SerialName("dt")
	val dateTime: Long,
	val sys: SysDto,
	val timezone: Long,
	val id: Int,
	@SerialName("name")
	val city: String
)

internal fun CurrentWeatherDto.toDomain(): CurrentWeather =
	CurrentWeather(
		weather = weather.first().toDomain(),
		main = main.toDomain(),
		wind = wind.toDomain(),
		visibility = visibility,
		clouds = clouds.toDomain(),
		dateTime = dateTime,
		sunMovement = sys.toSunMovementDomain(),
		timezone = timezone,
		id = id,
		city = city,
		country = sys.country
	)