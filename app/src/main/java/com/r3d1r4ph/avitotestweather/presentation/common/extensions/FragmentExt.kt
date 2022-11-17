package com.r3d1r4ph.avitotestweather.presentation.common.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.presentation.common.utils.SnackbarUtils

fun Fragment.showErrorSnack(
	text: String = getString(R.string.default_error),
	@BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) = SnackbarUtils.showErrorSnack(requireView(), text, duration)
