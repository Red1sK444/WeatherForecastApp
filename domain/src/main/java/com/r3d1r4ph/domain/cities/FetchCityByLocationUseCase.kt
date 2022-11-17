package com.r3d1r4ph.domain.cities

import com.r3d1r4ph.domain.common.SuspendedUseCase
import com.r3d1r4ph.domain.common.model.Location

interface FetchCityByLocationUseCase : SuspendedUseCase<Location, Unit>

class FetchCityByLocationUseCaseImpl(
	private val citiesRemoteDataSource: CitiesRemoteDataSource,
	private val cityLocalDataSource: CityLocalDataSource
) : FetchCityByLocationUseCase {

	override suspend fun execute(param: Location) {
		val city = citiesRemoteDataSource.getCityByLocation(param)
		cityLocalDataSource.setCity(city)
	}
}