package com.r3d1r4ph.data.network.common.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class CitiesQueryParamsInterceptor : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val modifiedUrl = request.url.newBuilder()
			.addQueryParameter(
				name = LANGUAGE_CODE_KEY,
				value = LANGUAGE_CODE_VALUE
			)
			.addQueryParameter(
				name = TYPES_KEY,
				value = TYPES_VALUE
			)
			.build()
		val newRequest = request.newBuilder()
			.url(modifiedUrl)
			.build()
		return chain.proceed(newRequest)
	}

	private companion object {

		const val LANGUAGE_CODE_KEY = "languageCode"
		const val LANGUAGE_CODE_VALUE = "ru"

		const val TYPES_KEY = "types"
		const val TYPES_VALUE = "CITY"
	}
}