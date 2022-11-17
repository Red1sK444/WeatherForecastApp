package com.r3d1r4ph.domain.weather.forecast

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.CurrentWeather
import com.r3d1r4ph.domain.weather.forecast.model.daily.forecast.DailyForecast
import com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast.HourlyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherForecastLocalDataSource {

	fun observeCurrentWeather(): Flow<CurrentWeather?>

	fun observeHourlyForecast(): Flow<List<HourlyForecast>>

	fun observeDailyForecast(): Flow<List<DailyForecast>>

	suspend fun setCurrentWeather(currentWeather: CurrentWeather)

	suspend fun setHourlyForecast(hourlyForecastList: List<HourlyForecast>)

	suspend fun setDailyForecast(dailyForecastList: List<DailyForecast>)
}