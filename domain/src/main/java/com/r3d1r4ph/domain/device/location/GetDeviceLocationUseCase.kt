package com.r3d1r4ph.domain.device.location

import com.r3d1r4ph.domain.common.SuspendedUseCase
import com.r3d1r4ph.domain.common.model.Location

interface GetDeviceLocationUseCase : SuspendedUseCase<Unit, Location>

class GetDeviceLocationUseCaseImpl(
	private val deviceLocationRemoteDataSource: DeviceLocationRemoteDataSource
) : GetDeviceLocationUseCase {

	override suspend fun execute(param: Unit): Location =
		deviceLocationRemoteDataSource.getDeviceLocation()
}