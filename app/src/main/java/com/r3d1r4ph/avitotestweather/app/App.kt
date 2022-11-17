package com.r3d1r4ph.avitotestweather.app

import android.app.Application
import com.r3d1r4ph.avitotestweather.app.di.KoinModules
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		initKoin()
	}

	@OptIn(ExperimentalSerializationApi::class)
	private fun initKoin() {
		startKoin {
			androidContext(this@App)
			modules(KoinModules.all)
		}
	}
}