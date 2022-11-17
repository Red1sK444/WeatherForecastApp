package com.r3d1r4ph.avitotestweather.presentation.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.location.LocationServices
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.replaceFragment
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.DashboardFragment
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherAction
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.SearchCityFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WeatherActivity : AppCompatActivity(), SearchCityFragment.SearchCityFragmentListener, DashboardFragment.DashboardFragmentListener {

	private val viewModel by viewModel<WeatherViewModel>()

	private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading() } }
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initObserver()
	}

	private fun initObserver() {
		viewModel.event.observe(this) { event ->
			event.getContentIfNotHandled()?.let { action ->
				handleAction(action)
			}
		}
	}

	private fun handleAction(action: WeatherAction) {
		when (action) {
			WeatherAction.OpenDashboardScreen     ->
				replaceFragment(
					R.id.mainFragmentContainer,
					DashboardFragment.newInstance(),
					DashboardFragment.TAG
				)

			is WeatherAction.OpenSearchCityScreen ->
				replaceFragment(
					R.id.mainFragmentContainer,
					SearchCityFragment.newInstance(),
					SearchCityFragment.TAG
				)
			WeatherAction.CheckCurrentLocation    -> checkCurrentLocation()
		}
	}

	@SuppressLint("MissingPermission", "SetTextI18n")
	private fun checkCurrentLocation() {
		if (isLocationPermissionGranted()) {
			fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
				viewModel.onLocationGet(task.result)
			}
		} else {
			requestPermissions()
		}
	}

	private fun isLocationPermissionGranted(): Boolean {
		if (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			return true
		}
		return false
	}

	private fun requestPermissions() {
		requestPermissions(
			arrayOf(ACCESS_COARSE_LOCATION),
			LOCATION_PERMISSION_ID
		)
	}

	@SuppressLint("MissingSuperCall")
	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		if (requestCode == LOCATION_PERMISSION_ID) {
			if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
				viewModel.onLocationPermissionGranted()
			} else {
				viewModel.onLocationPermissionDenied()
			}
		}
	}

	override fun onCitySelected() {
		replaceFragment(
			R.id.mainFragmentContainer,
			DashboardFragment.newInstance(),
			DashboardFragment.TAG,
			addToBackStack = false
		)
	}

	override fun onSearchCityClick() {
		replaceFragment(
			R.id.mainFragmentContainer,
			SearchCityFragment.newInstance(),
			SearchCityFragment.TAG
		)
	}

	private companion object {

		private const val LOCATION_PERMISSION_ID = 2
	}
}