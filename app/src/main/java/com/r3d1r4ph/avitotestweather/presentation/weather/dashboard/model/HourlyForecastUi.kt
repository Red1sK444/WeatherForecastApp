package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils.formatByPattern
import com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast.HourlyForecast

data class HourlyForecastUi(
	val formattedTime: String,
	val weatherIconUrl: String,
	val temperature: Int
)

internal fun HourlyForecast.toUi(): HourlyForecastUi =
	HourlyForecastUi(
		formattedTime = dateTime.formatByPattern(pattern = DateFormattingUtils.DateTimePattern.HOUR_MIN),
		weatherIconUrl = weather.icon,
		temperature = main.temperature.toInt()
	)
