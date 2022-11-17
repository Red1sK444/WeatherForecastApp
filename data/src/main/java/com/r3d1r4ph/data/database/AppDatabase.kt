package com.r3d1r4ph.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.r3d1r4ph.data.database.cities.CityDao
import com.r3d1r4ph.data.database.cities.model.CityEntity
import com.r3d1r4ph.data.database.weather.forecast.current.weather.CurrentWeatherDao
import com.r3d1r4ph.data.database.weather.forecast.current.weather.model.CurrentWeatherEntity
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.DailyForecastDao
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model.DailyForecastEntity
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.HourlyForecastDao
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.model.HourlyForecastEntity

@Database(
	entities = [CurrentWeatherEntity::class, HourlyForecastEntity::class, DailyForecastEntity::class, CityEntity::class],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {

	abstract fun getCurrentWeatherDao(): CurrentWeatherDao
	abstract fun getDailyForecastDao(): DailyForecastDao
	abstract fun getHourlyForecastDao(): HourlyForecastDao
	abstract fun getCityDao(): CityDao
}