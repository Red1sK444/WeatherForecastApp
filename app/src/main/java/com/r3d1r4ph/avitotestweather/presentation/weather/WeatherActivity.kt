package com.r3d1r4ph.avitotestweather.presentation.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.replaceFragment
import com.r3d1r4ph.avitotestweather.presentation.common.permissions.PermissionsManager.isLocationPermissionGranted
import com.r3d1r4ph.avitotestweather.presentation.common.permissions.PermissionsManager.requestLocationPermission
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.DashboardFragment
import com.r3d1r4ph.avitotestweather.presentation.weather.model.WeatherAction
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.SearchCityFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WeatherActivity : AppCompatActivity(), SearchCityFragment.SearchCityFragmentListener, DashboardFragment.DashboardFragmentListener {

	private val viewModel by viewModel<WeatherViewModel>()

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
			WeatherAction.OpenDashboardScreen       ->
				replaceFragment(
					R.id.mainFragmentContainer,
					DashboardFragment.newInstance(),
					DashboardFragment.TAG
				)

			is WeatherAction.OpenSearchCityScreen   ->
				replaceFragment(
					R.id.mainFragmentContainer,
					SearchCityFragment.newInstance(),
					SearchCityFragment.TAG
				)
			WeatherAction.CheckLocationPermission   -> checkLocationPermission()
			WeatherAction.RequestLocationPermission -> requestLocationPermission()
		}
	}

	private fun checkLocationPermission() {
		if (isLocationPermissionGranted()) {
			viewModel.onLocationPermissionGranted()
		} else {
			viewModel.onLocationPermissionDenied()
		}
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		viewModel.onRequestPermissionResult(requestCode, grantResults)
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
}