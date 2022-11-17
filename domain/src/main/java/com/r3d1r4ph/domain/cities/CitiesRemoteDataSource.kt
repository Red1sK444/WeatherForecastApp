package com.r3d1r4ph.domain.cities

import com.r3d1r4ph.domain.cities.model.City
import com.r3d1r4ph.domain.common.model.Location

interface CitiesRemoteDataSource {

	suspend fun getCities(cityPrefix: String, limit: Int): List<City>

	suspend fun getCityByLocation(location: Location): City
}