package com.r3d1r4ph.data.database.weather.forecast.current.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r3d1r4ph.data.database.weather.forecast.current.weather.model.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

	@Query("DELETE FROM current_weathers")
	suspend fun clear()

	@Transaction
	suspend fun replaceCurrentWeather(newCurrentWeatherEntity: CurrentWeatherEntity) {
		clear()
		insertCurrentWeather(newCurrentWeatherEntity)
	}

	@Query("SELECT * FROM current_weathers LIMIT 1")
	fun observeCurrentWeather(): Flow<CurrentWeatherEntity?>
}