package com.r3d1r4ph.domain.device.location

import com.r3d1r4ph.domain.common.model.Location

interface DeviceLocationRemoteDataSource {

	suspend fun getDeviceLocation(): Location
}