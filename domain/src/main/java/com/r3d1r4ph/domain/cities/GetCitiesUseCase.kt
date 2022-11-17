package com.r3d1r4ph.domain.cities

import com.r3d1r4ph.domain.cities.model.City
import com.r3d1r4ph.domain.cities.model.CityPrefix
import com.r3d1r4ph.domain.common.SuspendedUseCase

interface GetCitiesUseCase : SuspendedUseCase<CityPrefix, List<City>>

class GetCitiesUseCaseImpl(
	private val citiesRemoteDataSource: CitiesRemoteDataSource
) : GetCitiesUseCase {

	override suspend fun execute(param: CityPrefix): List<City> =
		citiesRemoteDataSource.getCities(cityPrefix = param.cityPrefix, limit = CITIES_LIMIT)

	private companion object {

		const val CITIES_LIMIT = 10
	}
}