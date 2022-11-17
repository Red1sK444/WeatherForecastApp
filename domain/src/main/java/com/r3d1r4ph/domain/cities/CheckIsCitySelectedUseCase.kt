package com.r3d1r4ph.domain.cities

import com.r3d1r4ph.domain.common.SuspendedUseCase

interface CheckIsCitySelectedUseCase : SuspendedUseCase<Unit, Boolean>

class CheckIsCitySelectedUseCaseImpl(
	private val cityLocalDataSource: CityLocalDataSource
) : CheckIsCitySelectedUseCase {

	override suspend fun execute(param: Unit): Boolean =
		cityLocalDataSource.getCity() != null
}