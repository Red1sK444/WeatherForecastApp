package com.r3d1r4ph.data.database.weather.forecast.hourly.forecast

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r3d1r4ph.data.database.weather.forecast.hourly.forecast.model.HourlyForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HourlyForecastDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertHourlyForecasts(hourlyForecastEntityList: List<HourlyForecastEntity>)

	@Query("DELETE FROM hourly_forecasts")
	suspend fun clear()

	@Transaction
	suspend fun replaceHourlyForecasts(newHourlyForecastEntityList: List<HourlyForecastEntity>) {
		clear()
		insertHourlyForecasts(newHourlyForecastEntityList)
	}

	@Query("SELECT * FROM hourly_forecasts")
	fun observeHourlyForecasts(): Flow<List<HourlyForecastEntity>>
}