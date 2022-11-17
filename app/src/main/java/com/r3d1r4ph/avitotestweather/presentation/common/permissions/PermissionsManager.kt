package com.r3d1r4ph.avitotestweather.presentation.common.permissions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionsManager {

	const val LOCATION_PERMISSION_REQUEST_CODE = 0x0001

	fun Context.isLocationPermissionGranted(): Boolean =
		isPermissionGranted(ACCESS_COARSE_LOCATION)

	fun AppCompatActivity.requestLocationPermission() {
		requestPermission(ACCESS_COARSE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE)
	}

	private fun AppCompatActivity.requestPermission(permission: String, requestCode: Int) {
		if (isPermissionGranted(permission).not()) {
			ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
		}
	}

	private fun Context.isPermissionGranted(permission: String): Boolean =
		ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

}