package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.ui.State
import com.r3d1r4ph.truedomain.weather.forecast.model.current.weather.CurrentWeather
import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.DailyForecast
import com.r3d1r4ph.truedomain.weather.forecast.model.hourly.forecast.HourlyForecast

data class DashboardState(
	val currentWeather: CurrentWeather?,
	val hourlyForecasts: List<HourlyForecast>,
	val dailyForecasts: List<DailyForecast>
) : State