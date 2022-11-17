package com.r3d1r4ph.data.network.weather.forecast.model.current.weather

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.Clouds
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CloudsDto(
	@SerialName("all")
	val cloudiness: Int
)

internal fun CloudsDto.toDomain(): Clouds =
	Clouds(
		cloudiness = cloudiness
	)
