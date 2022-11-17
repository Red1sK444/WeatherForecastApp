package com.r3d1r4ph.avitotestweather.app.di

import com.r3d1r4ph.avitotestweather.BuildConfig.CITIES_API_URL
import com.r3d1r4ph.avitotestweather.BuildConfig.WEATHER_API_URL
import com.r3d1r4ph.data.database.AppDatabase
import com.r3d1r4ph.data.database.DatabaseBuilder
import com.r3d1r4ph.data.database.cities.CityLocalDataSourceImpl
import com.r3d1r4ph.data.database.weather.forecast.WeatherForecastLocalDataSourceImpl
import com.r3d1r4ph.data.network.Network
import com.r3d1r4ph.data.network.cities.CitiesApi
import com.r3d1r4ph.data.network.cities.CitiesRemoteDataSourceImpl
import com.r3d1r4ph.data.network.common.interceptors.CitiesQueryParamsInterceptor
import com.r3d1r4ph.data.network.common.interceptors.WeatherQueryParamsInterceptor
import com.r3d1r4ph.data.network.weather.forecast.WeatherForecastApi
import com.r3d1r4ph.data.network.weather.forecast.WeatherForecastRemoteDataSourceImpl
import com.r3d1r4ph.domain.cities.CheckIsCitySelectedUseCase
import com.r3d1r4ph.domain.cities.CheckIsCitySelectedUseCaseImpl
import com.r3d1r4ph.domain.cities.CitiesRemoteDataSource
import com.r3d1r4ph.domain.cities.CityLocalDataSource
import com.r3d1r4ph.domain.cities.FetchCityByLocationUseCase
import com.r3d1r4ph.domain.cities.FetchCityByLocationUseCaseImpl
import com.r3d1r4ph.domain.cities.GetCitiesUseCase
import com.r3d1r4ph.domain.cities.GetCitiesUseCaseImpl
import com.r3d1r4ph.domain.cities.SaveSelectedCityUseCase
import com.r3d1r4ph.domain.cities.SaveSelectedCityUseCaseImpl
import com.r3d1r4ph.domain.weather.forecast.FetchWeatherForecastUseCase
import com.r3d1r4ph.domain.weather.forecast.FetchWeatherForecastUseCaseImpl
import com.r3d1r4ph.domain.weather.forecast.ObserveWeatherForecastUseCase
import com.r3d1r4ph.domain.weather.forecast.ObserveWeatherForecastUseCaseImpl
import com.r3d1r4ph.domain.weather.forecast.WeatherForecastLocalDataSource
import com.r3d1r4ph.domain.weather.forecast.WeatherForecastRemoteDataSource
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.BuildConfig
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

object KoinModules {

	@ExperimentalSerializationApi
	private val networkModule = module {
		single { Network.appJson }
		single(named(WEATHER_API)) {
			Network.getHttpClient(
				interceptors = listOf(WeatherQueryParamsInterceptor()),
				isDebug = BuildConfig.DEBUG
			)
		}
		single(named(CITIES_API)) {
			Network.getHttpClient(
				interceptors = listOf(CitiesQueryParamsInterceptor()),
				isDebug = BuildConfig.DEBUG
			)
		}
		single(named(WEATHER_API)) {
			Network.getRetrofit(
				client = get(named(WEATHER_API)),
				url = WEATHER_API_URL,
				json = get()
			)
		}
		single(named(CITIES_API)) {
			Network.getRetrofit(
				client = get(named(CITIES_API)),
				url = CITIES_API_URL,
				json = get()
			)
		}
	}

	private val apiModule = module {
		single<WeatherForecastApi> { Network.getApi(retrofit = get(named(WEATHER_API))) }
		single<CitiesApi> { Network.getApi(retrofit = get(named(CITIES_API))) }
	}

	private val databaseModule = module {
		singleOf(DatabaseBuilder::build)
		singleOf(AppDatabase::getCurrentWeatherDao)
		singleOf(AppDatabase::getDailyForecastDao)
		singleOf(AppDatabase::getHourlyForecastDao)
		singleOf(AppDatabase::getCityDao)
	}

	private val dataSourceModule = module {
		factoryOf(::WeatherForecastRemoteDataSourceImpl) bind WeatherForecastRemoteDataSource::class
		factoryOf(::WeatherForecastLocalDataSourceImpl) bind WeatherForecastLocalDataSource::class
		factoryOf(::CitiesRemoteDataSourceImpl) bind CitiesRemoteDataSource::class
		factoryOf(::CityLocalDataSourceImpl) bind CityLocalDataSource::class
	}

	private val useCaseModule = module {
		factoryOf(::FetchWeatherForecastUseCaseImpl) bind FetchWeatherForecastUseCase::class
		factoryOf(::GetCitiesUseCaseImpl) bind GetCitiesUseCase::class
		factoryOf(::SaveSelectedCityUseCaseImpl) bind SaveSelectedCityUseCase::class
		factoryOf(::CheckIsCitySelectedUseCaseImpl) bind CheckIsCitySelectedUseCase::class
		factoryOf(::ObserveWeatherForecastUseCaseImpl) bind ObserveWeatherForecastUseCase::class
		factoryOf(::FetchCityByLocationUseCaseImpl) bind FetchCityByLocationUseCase::class
	}

	@ExperimentalSerializationApi
	val all = listOf(
		networkModule,
		apiModule,
		useCaseModule,
		dataSourceModule,
		presentationModule,
		databaseModule
	)

	private const val WEATHER_API = "WEATHER_API"
	private const val CITIES_API = "CITIES_API"
}