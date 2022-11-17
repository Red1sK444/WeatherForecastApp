package com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r3d1r4ph.data.database.weather.forecast.common.model.WeatherDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDomain
import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.DailyForecast

@Entity(tableName = "daily_forecasts")
data class DailyForecastEntity(
	@PrimaryKey(autoGenerate = false)
	val dateTime: Long,
	val sunrise: Long,
	val sunset: Long,
	@Embedded(prefix = "temperature_")
	val temperature: TemperatureDb,
	@Embedded(prefix = "feels_like_")
	val feelsLike: FeelsLikeDb,
	val pressure: Int,
	val humidity: Int,
	@Embedded(prefix = "weather_")
	val weather: WeatherDb,
	val speed: Float
)

internal fun DailyForecastEntity.toDomain(): DailyForecast =
	DailyForecast(
		dateTime = dateTime,
		sunrise = sunrise,
		sunset = sunset,
		temperature = temperature.toDomain(),
		feelsLike = feelsLike.toDomain(),
		pressure = pressure,
		humidity = humidity,
		weather = weather.toDomain(),
		speed = speed
	)

internal fun DailyForecast.toDb(): DailyForecastEntity =
	DailyForecastEntity(
		dateTime = dateTime,
		sunrise = sunrise,
		sunset = sunset,
		temperature = temperature.toDb(),
		feelsLike = feelsLike.toDb(),
		pressure = pressure,
		humidity = humidity,
		weather = weather.toDb(),
		speed = speed
	)