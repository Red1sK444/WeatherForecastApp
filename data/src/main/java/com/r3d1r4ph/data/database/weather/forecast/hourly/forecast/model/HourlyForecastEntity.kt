package com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r3d1r4ph.data.database.weather.forecast.common.model.MainInfoDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.WeatherDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.common.model.toDomain
import com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast.HourlyForecast

@Entity(tableName = "hourly_forecasts")
data class HourlyForecastEntity(
	@PrimaryKey(autoGenerate = false)
	val dateTime: Long,
	@Embedded(prefix = "main_")
	val main: MainInfoDb,
	@Embedded(prefix = "weather_")
	val weather: WeatherDb,
	val visibility: Int,
	val precipitationProbability: Float,
	val formattedDate: String
)

internal fun HourlyForecastEntity.toDomain(): HourlyForecast =
	HourlyForecast(
		dateTime = dateTime,
		main = main.toDomain(),
		weather = weather.toDomain(),
		visibility = visibility,
		precipitationProbability = precipitationProbability,
		formattedDate = formattedDate
	)

internal fun HourlyForecast.toDb(): HourlyForecastEntity =
	HourlyForecastEntity(
		dateTime = dateTime,
		main = main.toDb(),
		weather = weather.toDb(),
		visibility = visibility,
		precipitationProbability = precipitationProbability,
		formattedDate = formattedDate
	)
