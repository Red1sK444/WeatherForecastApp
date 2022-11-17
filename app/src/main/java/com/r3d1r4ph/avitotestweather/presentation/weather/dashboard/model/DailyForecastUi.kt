package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model

import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils.formatByPattern
import com.r3d1r4ph.domain.weather.forecast.model.daily.forecast.DailyForecast

data class DailyForecastUi(
	val formattedDateTime: String,
	val weatherIconUrl: String,
	val temperature: Int,
	val minTemperature: Int,
	val maxTemperature: Int
)

internal fun DailyForecast.toUi(): DailyForecastUi =
	DailyForecastUi(
		formattedDateTime = dateTime.formatByPattern(pattern = DateFormattingUtils.DateTimePattern.DAY_MONTH),
		weatherIconUrl = weather.icon,
		temperature = temperature.day.toInt(),
		minTemperature = temperature.min.toInt(),
		maxTemperature = temperature.max.toInt()
	)
