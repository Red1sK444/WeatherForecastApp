package com.r3d1r4ph.data.network.weather.forecast.model.current.weather

import com.r3d1r4ph.truedomain.weather.forecast.model.current.weather.Wind
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WindDto(
	val speed: Double,
	@SerialName("deg")
	val direction: Double,
	val gust: Double? = null
)

internal fun WindDto.toDomain(): Wind =
	Wind(
		speed = speed,
		direction = direction,
		gust = gust
	)
