package com.r3d1r4ph.avitotestweather.app.di

import com.r3d1r4ph.avitotestweather.presentation.weather.WeatherViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.DashboardViewModel
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.SearchCityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val mainModule = module {
	viewModelOf(::WeatherViewModel)
}

private val dashboardModule = module {
	viewModelOf(::DashboardViewModel)
}

private val searchCityModule = module {
	viewModelOf(::SearchCityViewModel)
}

val presentationModule = module {
	loadKoinModules(
		listOf(
			mainModule,
			dashboardModule,
			searchCityModule
		)
	)
}