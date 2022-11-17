package com.r3d1r4ph.truedomain.cities

import com.r3d1r4ph.truedomain.cities.model.City

interface CityLocalDataSource {

	suspend fun getCity(): City?

	suspend fun setCity(city: City)
}