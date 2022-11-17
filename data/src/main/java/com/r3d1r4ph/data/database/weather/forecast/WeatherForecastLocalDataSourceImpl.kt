package com.r3d1r4ph.data.database.weather.forecast

import com.r3d1r4ph.data.database.weather.forecast.current.weather.CurrentWeatherDao
import com.r3d1r4ph.data.database.weather.forecast.current.weather.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.current.weather.model.toDomain
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.DailyForecastDao
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model.toDomain
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.HourlyForecastDao
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.model.toDb
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.model.toDomain
import com.r3d1r4ph.truedomain.weather.forecast.WeatherForecastLocalDataSource
import com.r3d1r4ph.truedomain.weather.forecast.model.current.weather.CurrentWeather
import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.DailyForecast
import com.r3d1r4ph.truedomain.weather.forecast.model.hourly.forecast.HourlyForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherForecastLocalDataSourceImpl(
	private val currentWeatherDao: CurrentWeatherDao,
	private val hourlyForecastDao: HourlyForecastDao,
	private val dailyForecastDao: DailyForecastDao
) : WeatherForecastLocalDataSource {

	override fun observeCurrentWeather(): Flow<CurrentWeather?> =
		currentWeatherDao.observeCurrentWeather().map { it?.toDomain() }

	override fun observeHourlyForecast(): Flow<List<HourlyForecast>> =
		hourlyForecastDao.observeHourlyForecasts().map { list -> list.map { it.toDomain() } }

	override fun observeDailyForecast(): Flow<List<DailyForecast>> =
		dailyForecastDao.observeDailyForecasts().map { list -> list.map { it.toDomain() } }

	override suspend fun setCurrentWeather(currentWeather: CurrentWeather) {
		currentWeatherDao.replaceCurrentWeather(currentWeather.toDb())
	}

	override suspend fun setHourlyForecast(hourlyForecastList: List<HourlyForecast>) {
		hourlyForecastDao.replaceHourlyForecasts(hourlyForecastList.map { it.toDb() })
	}

	override suspend fun setDailyForecast(dailyForecastList: List<DailyForecast>) {
		dailyForecastDao.replaceDailyForecasts(dailyForecastList.map { it.toDb() })
	}
}