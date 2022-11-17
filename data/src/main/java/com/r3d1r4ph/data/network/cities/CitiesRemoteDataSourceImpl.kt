package com.r3d1r4ph.data.network.cities

import com.r3d1r4ph.data.network.cities.model.toDomain
import com.r3d1r4ph.domain.cities.CitiesRemoteDataSource
import com.r3d1r4ph.domain.cities.model.City
import com.r3d1r4ph.domain.common.model.Location

class CitiesRemoteDataSourceImpl(
	private val citiesApi: CitiesApi
) : CitiesRemoteDataSource {

	override suspend fun getCities(cityPrefix: String, limit: Int): List<City> =
		citiesApi.getCities(cityPrefix, limit).data.map { it.toDomain() }

	override suspend fun getCityByLocation(location: Location): City =
		requireNotNull(citiesApi.getCity(locationId = location.formatted(), limit = 1).data.first()).toDomain()

	private fun Location.formatted(): String = buildString {
		append(if (latitude > 0) PLUS else MINUS)
		append(latitude)
		append(if (longitude > 0) PLUS else MINUS)
		append(longitude)
	}

	private companion object {

		const val PLUS = '+'
		const val MINUS = '-'
	}
}