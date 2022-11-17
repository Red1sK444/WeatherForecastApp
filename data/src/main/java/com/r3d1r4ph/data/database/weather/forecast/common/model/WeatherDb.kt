package com.r3d1r4ph.data.database.weather.forecast.common.model

import com.r3d1r4ph.truedomain.weather.forecast.model.Weather

data class WeatherDb(
	val id: Int,
	val main: String,
	val description: String,
	val icon: String
)

internal fun WeatherDb.toDomain(): Weather =
	Weather(
		id = id,
		main = main,
		description = description,
		icon = icon
	)

internal fun Weather.toDb(): WeatherDb =
	WeatherDb(
		id = id,
		main = main,
		description = description,
		icon = icon
	)
