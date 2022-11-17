package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.Action

sealed class DashboardAction : Action {
	object ShowError : DashboardAction()
	object OpenSearchCityScreen : DashboardAction()
	object RequestLocationPermission : DashboardAction()
}
