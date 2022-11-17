package com.r3d1r4ph.data.network.cities

import com.r3d1r4ph.data.network.cities.model.CitiesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CitiesApi {

	@GET("geo/cities")
	suspend fun getCities(
		@Query("namePrefix") cityPrefix: String,
		@Query("limit") limit: Int
	): CitiesResponseDto

	@GET("geo/locations/{locationId}/nearbyCities")
	suspend fun getCity(
		@Path("locationId") locationId: String,
		@Query("limit") limit: Int
	): CitiesResponseDto
}