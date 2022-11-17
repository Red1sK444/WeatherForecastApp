package com.r3d1r4ph.truedomain.cities.model

data class City(
	val id: Int,
	val name: String,
	val country: String,
	val region: String,
	val latitude: Float,
	val longitude: Float
)