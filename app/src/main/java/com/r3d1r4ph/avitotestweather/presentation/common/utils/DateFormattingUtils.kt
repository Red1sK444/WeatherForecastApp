package com.r3d1r4ph.avitotestweather.presentation.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormattingUtils {

	private const val MILLIS_IN_ONE_SEC = 1000
	private val ruLocale = Locale("RU")

	fun Long.formatByPattern(
		pattern: DateTimePattern = DateTimePattern.HOUR_MIN_DAY_MONTH_YEAR
	): String = SimpleDateFormat(pattern.pattern, ruLocale).format(Date(this * MILLIS_IN_ONE_SEC))

	enum class DateTimePattern(val pattern: String) {
		/**
		 * 1 september
		 **/
		HOUR_MIN("HH:mm"),
		/**
		 * 1 september
		 **/
		DAY_MONTH("dd MMMM"),

		/**
		 * 12:00 • 1 september 2022
		 **/
		HOUR_MIN_DAY_MONTH_YEAR("HH:mm • dd MMMM yyyy")
	}
}