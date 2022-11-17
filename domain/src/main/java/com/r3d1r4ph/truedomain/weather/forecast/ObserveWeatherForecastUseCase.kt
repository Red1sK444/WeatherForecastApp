package com.r3d1r4ph.truedomain.weather.forecast

import com.r3d1r4ph.truedomain.common.FlowUseCase
import com.r3d1r4ph.truedomain.weather.forecast.model.WeatherForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface ObserveWeatherForecastUseCase : FlowUseCase<Unit, WeatherForecast>

class ObserveWeatherForecastUseCaseImpl(
	private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource
) : ObserveWeatherForecastUseCase {

	override fun execute(param: Unit): Flow<Result<WeatherForecast>> {
		val currentWeatherFlow = weatherForecastLocalDataSource.observeCurrentWeather()
		val hourlyForecastsFlow = weatherForecastLocalDataSource.observeHourlyForecast()
		val dailyForecastsFlow = weatherForecastLocalDataSource.observeDailyForecast()

		return combine(
			currentWeatherFlow,
			hourlyForecastsFlow,
			dailyForecastsFlow
		) { currentWeather, hourlyForecasts, dailyForecasts ->
			Result.success(
				WeatherForecast(
					currentWeather = currentWeather,
					hourlyForecasts = hourlyForecasts,
					dailyForecasts = dailyForecasts
				)
			)
		}
	}
}