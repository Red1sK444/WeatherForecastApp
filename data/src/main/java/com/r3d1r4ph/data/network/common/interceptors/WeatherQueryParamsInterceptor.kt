package com.r3d1r4ph.data.network.common.interceptors

import com.r3d1r4ph.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class WeatherQueryParamsInterceptor : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val modifiedUrl = request.url.newBuilder()
			.addQueryParameter(
				name = APP_ID_KEY,
				value = BuildConfig.WEATHER_API_KEY
			)
			.addQueryParameter(
				name = UNITS_KEY,
				value = UNITS_VALUE
			)
			.addQueryParameter(
				name = LANG_KEY,
				value = LANG_VALUE
			)
			.build()
		val newRequest = request.newBuilder()
			.url(modifiedUrl)
			.build()
		return chain.proceed(newRequest)
	}

	private companion object {

		const val APP_ID_KEY = "appid"

		const val UNITS_KEY = "units"
		const val UNITS_VALUE = "metric"

		const val LANG_KEY = "lang"
		const val LANG_VALUE = "ru"
	}
}