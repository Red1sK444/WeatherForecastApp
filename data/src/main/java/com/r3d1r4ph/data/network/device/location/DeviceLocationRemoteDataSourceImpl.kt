package com.r3d1r4ph.data.network.device.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.r3d1r4ph.domain.common.model.Location
import com.r3d1r4ph.domain.device.location.DeviceLocationRemoteDataSource
import kotlinx.coroutines.tasks.await

class DeviceLocationRemoteDataSourceImpl(
	context: Context
) : DeviceLocationRemoteDataSource {

	private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

	@SuppressLint("MissingPermission")
	override suspend fun getDeviceLocation(): Location = fusedLocationClient.lastLocation.await().toDomain()

	private fun android.location.Location.toDomain(): Location =
		Location(
			longitude = longitude.toFloat(),
			latitude = latitude.toFloat()
		)
}