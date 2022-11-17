package com.r3d1r4ph.avitotestweather.presentation.common.utils

import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.r3d1r4ph.avitotestweather.R

object SnackbarUtils {

	fun showErrorSnack(view: View, text: String, @BaseTransientBottomBar.Duration duration: Int) {
		with(view.context) {
			showSnackbar(
				view = view,
				text = text,
				duration = duration,
				textColor = getColor(R.color.dark_white),
				bgColor = getColor(R.color.red)
			)
		}
	}

	private fun showSnackbar(
		view: View,
		text: String,
		@BaseTransientBottomBar.Duration duration: Int,
		@ColorInt textColor: Int,
		@ColorInt bgColor: Int,
	) {
		Snackbar.make(view, text, duration)
			.setTextColor(textColor)
			.setBackgroundTint(bgColor)
			.show()
	}
}