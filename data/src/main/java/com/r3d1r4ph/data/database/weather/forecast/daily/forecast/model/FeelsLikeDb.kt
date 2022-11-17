package com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model

import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.FeelsLike

data class FeelsLikeDb(
	val day: Float,
	val night: Float,
	val evening: Float,
	val morning: Float
)

internal fun FeelsLikeDb.toDomain(): FeelsLike =
	FeelsLike(
		day = day,
		night = night,
		evening = evening,
		morning = morning
	)

internal fun FeelsLike.toDb(): FeelsLikeDb =
	FeelsLikeDb(
		day = day,
		night = night,
		evening = evening,
		morning = morning
	)
