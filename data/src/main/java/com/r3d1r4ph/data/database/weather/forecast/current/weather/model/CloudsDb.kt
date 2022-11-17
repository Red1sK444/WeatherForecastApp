package com.r3d1r4ph.data.database.weather.forecast.current.weather.model

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.Clouds

data class CloudsDb(
	val cloudiness: Int
)

internal fun CloudsDb.toDomain(): Clouds =
	Clouds(
		cloudiness = cloudiness
	)

internal fun Clouds.toDb(): CloudsDb =
	CloudsDb(
		cloudiness = cloudiness
	)
