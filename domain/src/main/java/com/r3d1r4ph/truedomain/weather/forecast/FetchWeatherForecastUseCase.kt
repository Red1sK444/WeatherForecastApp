package com.r3d1r4ph.truedomain.weather.forecast

import com.r3d1r4ph.truedomain.cities.CityLocalDataSource
import com.r3d1r4ph.truedomain.common.SuspendedUseCase
import java.io.IOException

interface FetchWeatherForecastUseCase : SuspendedUseCase<Unit, Unit>

class FetchWeatherForecastUseCaseImpl(
	private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource,
	private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource,
	private val cityLocalDataSource: CityLocalDataSource
) : FetchWeatherForecastUseCase {

	override suspend fun execute(param: Unit) {
		val city = cityLocalDataSource.getCity() ?: throw IOException()

		val currentWeather = weatherForecastRemoteDataSource.getCurrentWeather(city = city.name)
		val hourlyForecasts = weatherForecastRemoteDataSource.getHourlyForecast(
			city = city.name,
			timestampsCount = HOURS_IN_DAY_COUNT
		)
		val dailyForecasts = weatherForecastRemoteDataSource.getDailyForecast(
			city = city.name,
			daysCount = DAYS_IN_WEEK_COUNT
		)

		weatherForecastLocalDataSource.setCurrentWeather(currentWeather)
		weatherForecastLocalDataSource.setHourlyForecast(hourlyForecasts)
		weatherForecastLocalDataSource.setDailyForecast(dailyForecasts)
	}

	private companion object {

		const val HOURS_IN_DAY_COUNT = 24
		const val DAYS_IN_WEEK_COUNT = 7
	}
}