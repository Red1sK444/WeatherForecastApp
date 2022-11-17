package com.r3d1r4ph.data.database.weather.forecast.common.model

import com.r3d1r4ph.domain.weather.forecast.model.MainInfo

data class MainInfoDb(
	val temperature: Float,
	val feelsLike: Float,
	val minTemperature: Float,
	val maxTemperature: Float,
	val pressure: Float,
	val humidity: Int
)

internal fun MainInfoDb.toDomain(): MainInfo =
	MainInfo(
		temperature = temperature,
		feelsLike = feelsLike,
		minTemperature = minTemperature,
		maxTemperature = maxTemperature,
		pressure = pressure,
		humidity = humidity
	)

internal fun MainInfo.toDb(): MainInfoDb =
	MainInfoDb(
		temperature = temperature,
		feelsLike = feelsLike,
		minTemperature = minTemperature,
		maxTemperature = maxTemperature,
		pressure = pressure,
		humidity = humidity
	)