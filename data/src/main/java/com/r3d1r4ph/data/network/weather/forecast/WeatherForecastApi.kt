package com.r3d1r4ph.data.network.weather.forecast

import com.r3d1r4ph.data.network.weather.forecast.model.current.weather.CurrentWeatherDto
import com.r3d1r4ph.data.network.weather.forecast.model.daily.forecast.DailyForecastResponseDto
import com.r3d1r4ph.data.network.weather.forecast.model.hourly.forecast.HourlyForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {

	@GET("weather")
	suspend fun getCurrentWeather(
		@Query("q") city: String
	): CurrentWeatherDto

	@GET("forecast/hourly")
	suspend fun getHourlyForecast(
		@Query("q") city: String,
		@Query("cnt") timestampsCount: Int
	): HourlyForecastResponseDto

	@GET("forecast/daily")
	suspend fun getDailyForecast(
		@Query("q") city: String,
		@Query("cnt") daysCount: Int
	): DailyForecastResponseDto
}