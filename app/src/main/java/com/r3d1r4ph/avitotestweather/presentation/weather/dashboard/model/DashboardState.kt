package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.State

data class DashboardState(
	val currentWeatherUi: CurrentWeatherUi?,
	val hourlyForecastUis: List<HourlyForecastUi>,
	val dailyForecastUis: List<DailyForecastUi>
) : State