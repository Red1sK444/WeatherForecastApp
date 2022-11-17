package com.r3d1r4ph.data.network.weather.forecast

import com.r3d1r4ph.data.network.weather.forecast.model.current.weather.toDomain
import com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast.toDomain
import com.r3d1r4ph.data.network.weather.forecast.model.hourly.forecast.toDomain
import com.r3d1r4ph.domain.weather.forecast.WeatherForecastRemoteDataSource
import com.r3d1r4ph.domain.weather.forecast.model.current.weather.CurrentWeather
import com.r3d1r4ph.domain.weather.forecast.model.daily.forecast.DailyForecast
import com.r3d1r4ph.domain.weather.forecast.model.hourly.forecast.HourlyForecast

class WeatherForecastRemoteDataSourceImpl(
	private val weatherForecastApi: WeatherForecastApi
) : WeatherForecastRemoteDataSource {

	override suspend fun getCurrentWeather(city: String): CurrentWeather =
		weatherForecastApi.getCurrentWeather(city = city).toDomain()

	override suspend fun getHourlyForecast(city: String, timestampsCount: Int): List<HourlyForecast> =
		weatherForecastApi.getHourlyForecast(city = city, timestampsCount = timestampsCount).hourlyForecasts.map { it.toDomain() }

	override suspend fun getDailyForecast(city: String, daysCount: Int): List<DailyForecast> =
		weatherForecastApi.getDailyForecast(city = city, daysCount = daysCount).dailyForecasts.map { it.toDomain() }

}