package com.r3d1r4ph.domain.weather.forecast

import com.r3d1r4ph.domain.weather.forecast.model.current.weather.CurrentWeather
import com.r3d1r4ph.domain.weather.forecast.model.daily.forecast.DailyForecast
import com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast.HourlyForecast

interface WeatherForecastRemoteDataSource {

	suspend fun getCurrentWeather(city: String): CurrentWeather

	suspend fun getHourlyForecast(city: String, timestampsCount: Int): List<HourlyForecast>

	suspend fun getDailyForecast(city: String, daysCount: Int): List<DailyForecast>
}