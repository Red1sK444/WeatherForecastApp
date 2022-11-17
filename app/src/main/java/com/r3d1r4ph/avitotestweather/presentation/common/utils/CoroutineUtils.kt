package com.r3d1r4ph.avitotestweather.presentation.common.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object CoroutineUtils {

	fun <T> debounce(
		waitMs: Long = 300L,
		scope: CoroutineScope,
		destinationFunction: suspend (T) -> Unit
	): (T) -> Unit {
		var debounceJob: Job? = null
		return { param: T ->
			debounceJob?.cancel()
			debounceJob = scope.launch {
				delay(waitMs)
				destinationFunction(param)
			}
		}
	}
}