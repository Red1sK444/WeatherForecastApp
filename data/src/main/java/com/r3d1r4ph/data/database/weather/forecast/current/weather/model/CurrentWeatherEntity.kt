package com.r3d1r4ph.data.database.weather.forecast.current.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r3d1r4ph.data.database.weather.forecast.common.model.MainInfoDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.WeatherDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDomain
import com.r3d1r4ph.domain.weather.forecast.model.current.weather.CurrentWeather

@Entity(tableName = "current_weathers")
data class CurrentWeatherEntity(
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	@Embedded(prefix = "weather_")
	val weather: WeatherDb,
	@Embedded(prefix = "main_")
	val main: MainInfoDb,
	val visibility: Int,
	@Embedded(prefix = "wind_")
	val wind: WindDb,
	@Embedded(prefix = "clouds_")
	val clouds: CloudsDb,
	val dateTime: Long,
	@Embedded(prefix = "sun_movement_")
	val sunMovement: SunMovementDb,
	val timezone: Long,
	val city: String,
	val country: String
)

internal fun CurrentWeatherEntity.toDomain(): CurrentWeather =
	CurrentWeather(
		weather = weather.toDomain(),
		main = main.toDomain(),
		wind = wind.toDomain(),
		visibility = visibility,
		clouds = clouds.toDomain(),
		dateTime = dateTime,
		sunMovement = sunMovement.toDomain(),
		timezone = timezone,
		id = id,
		city = city,
		country = country
	)

internal fun CurrentWeather.toDb(): CurrentWeatherEntity =
	CurrentWeatherEntity(
		weather = weather.toDb(),
		main = main.toDb(),
		wind = wind.toDb(),
		visibility = visibility,
		clouds = clouds.toDb(),
		dateTime = dateTime,
		sunMovement = sunMovement.toDb(),
		timezone = timezone,
		id = id,
		city = city,
		country = country
	)