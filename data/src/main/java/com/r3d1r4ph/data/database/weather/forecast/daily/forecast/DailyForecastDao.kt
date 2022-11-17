package com.r3d1r4ph.data.database.weather.forecast.daily.forecast

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r3d1r4ph.data.database.weather.forecast.daily.forecast.model.DailyForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyForecastDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertDailyForecasts(dailyForecastEntityList: List<DailyForecastEntity>)

	@Query("DELETE FROM daily_forecasts")
	suspend fun clear()

	@Transaction
	suspend fun replaceDailyForecasts(newDailyForecastEntityList: List<DailyForecastEntity>) {
		clear()
		insertDailyForecasts(newDailyForecastEntityList)
	}

	@Query("SELECT * FROM daily_forecasts")
	fun observeDailyForecasts(): Flow<List<DailyForecastEntity>>
}