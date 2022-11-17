package com.r3d1r4ph.domain.cities

import com.r3d1r4ph.domain.cities.model.City
import com.r3d1r4ph.domain.common.SuspendedUseCase

interface SaveSelectedCityUseCase : SuspendedUseCase<City, Unit>

class SaveSelectedCityUseCaseImpl(
	private val cityLocalDataSource: CityLocalDataSource
) : SaveSelectedCityUseCase {

	override suspend fun execute(param: City) {
		cityLocalDataSource.setCity(param)
	}
}