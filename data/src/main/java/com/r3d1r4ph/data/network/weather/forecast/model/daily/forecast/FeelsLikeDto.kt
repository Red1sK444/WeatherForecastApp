package com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast

import com.r3d1r4ph.domain.weather.forecast.model.daily.forecast.FeelsLike
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FeelsLikeDto(
	val day: Float,
	val night: Float,
	@SerialName("eve")
	val evening: Float,
	@SerialName("morn")
	val morning: Float
)

internal fun FeelsLikeDto.toDomain(): FeelsLike =
	FeelsLike(
		day = day,
		night = night,
		evening = evening,
		morning = morning
	)
