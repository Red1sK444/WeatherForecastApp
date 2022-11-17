package com.r3d1r4ph.data.database.cities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r3d1r4ph.data.database.cities.model.CityEntity

@Dao
interface CityDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCity(cityEntity: CityEntity)

	@Query("DELETE FROM cities")
	suspend fun clear()

	@Transaction
	suspend fun replaceCity(newCityEntity: CityEntity) {
		clear()
		insertCity(newCityEntity)
	}

	@Query("SELECT * FROM cities LIMIT 1")
	suspend fun getCity(): CityEntity?
}