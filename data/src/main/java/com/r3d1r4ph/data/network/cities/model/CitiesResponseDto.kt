package com.r3d1r4ph.data.network.cities.model

@kotlinx.serialization.Serializable
data class CitiesResponseDto(
	val data: List<CityDto>
)
