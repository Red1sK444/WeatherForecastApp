package com.r3d1r4ph.data.database.weather.forecast.current.weather.model

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.Wind

data class WindDb(
	val speed: Double,
	val direction: Double,
	val gust: Double?
)

internal fun WindDb.toDomain(): Wind =
	Wind(
		speed = speed,
		direction = direction,
		gust = gust
	)

internal fun Wind.toDb(): WindDb =
	WindDb(
		speed = speed,
		direction = direction,
		gust = gust
	)
