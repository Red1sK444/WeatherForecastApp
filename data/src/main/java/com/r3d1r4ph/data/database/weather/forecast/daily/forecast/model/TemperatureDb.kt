package com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model

import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.Temperature

data class TemperatureDb(
	val day: Float,
	val min: Float,
	val max: Float,
	val night: Float,
	val evening: Float,
	val morning: Float,
)

internal fun TemperatureDb.toDomain(): Temperature =
	Temperature(
		day = day,
		min = min,
		max = max,
		night = night,
		evening = evening,
		morning = morning
	)

internal fun Temperature.toDb(): TemperatureDb =
	TemperatureDb(
		day = day,
		min = min,
		max = max,
		night = night,
		evening = evening,
		morning = morning
	)