package com.r3d1r4ph.avitotestweather.presentation.common.extensions

import android.content.res.Resources

val Int.dpToPx
	get() = (this * Resources.getSystem().displayMetrics.density).toInt()