package com.r3d1r4ph.data.database.weather.forecast.current.weather.model

import com.r3d1r4ph.truedomain.weather.forecast.model.current.weather.SunMovement

data class SunMovementDb(
	val sunrise: Long,
	val sunset: Long
)

internal fun SunMovementDb.toDomain(): SunMovement =
	SunMovement(
		sunrise = sunrise,
		sunset = sunset
	)

internal fun SunMovement.toDb(): SunMovementDb =
	SunMovementDb(
		sunrise = sunrise,
		sunset = sunset
	)
