package com.r3d1r4ph.data.network.cities.model

import com.r3d1r4ph.truedomain.cities.model.City

@kotlinx.serialization.Serializable
data class CityDto(
	val id: Int,
	val name: String,
	val country: String,
	val region: String,
	val latitude: Float,
	val longitude: Float
)

internal fun CityDto.toDomain(): City =
	City(
		id = id,
		name = name,
		country = country,
		region = region,
		latitude = latitude,
		longitude = longitude
	)