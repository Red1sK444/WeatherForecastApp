package com.r3d1r4ph.data.network.weather.forecast.model.current.weather

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.SunMovement

@kotlinx.serialization.Serializable
data class SysDto(
	val country: String,
	val sunrise: Long,
	val sunset: Long
)

internal fun SysDto.toSunMovementDomain(): SunMovement =
	SunMovement(
		sunrise = sunrise,
		sunset = sunset
	)