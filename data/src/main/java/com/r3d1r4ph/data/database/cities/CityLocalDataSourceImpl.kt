package com.r3d1r4ph.data.database.cities

import com.r3d1r4ph.data.database.cities.model.toDb
import com.r3d1r4ph.data.database.cities.model.toDomain
import com.r3d1r4ph.truedomain.cities.CityLocalDataSource
import com.r3d1r4ph.truedomain.cities.model.City

class CityLocalDataSourceImpl(
	private val cityDao: CityDao
) : CityLocalDataSource {

	override suspend fun getCity(): City? =
		cityDao.getCity()?.toDomain()

	override suspend fun setCity(city: City) {
		cityDao.replaceCity(city.toDb())
	}
}