package com.r3d1r4ph.data.database.cities.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r3d1r4ph.truedomain.cities.model.City

@Entity(tableName = "cities")
data class CityEntity(
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	val name: String,
	val country: String,
	val region: String,
	val latitude: Float,
	val longitude: Float
)

internal fun CityEntity.toDomain(): City =
	City(
		id = id,
		name = name,
		country = country,
		region = region,
		latitude = latitude,
		longitude = longitude
	)

internal fun City.toDb(): CityEntity =
	CityEntity(
		id = id,
		name = name,
		country = country,
		region = region,
		latitude = latitude,
		longitude = longitude
	)