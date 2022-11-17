package com.r3d1r4ph.data.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

	private const val DATABASE_NAME = "app_database"

	fun build(context: Context): AppDatabase = Room
		.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
		.build()
}