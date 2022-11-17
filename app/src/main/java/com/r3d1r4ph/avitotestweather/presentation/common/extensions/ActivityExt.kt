package com.r3d1r4ph.avitotestweather.presentation.common.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun AppCompatActivity.replaceFragment(
	@IdRes containerId: Int,
	fragment: Fragment,
	fragmentTag: String,
	addToBackStack: Boolean = true
) {
	val currentFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
	if (currentFragment?.isVisible == true) return

	supportFragmentManager.commit {
		if (addToBackStack) {
			addToBackStack(fragmentTag)
		}
		replace(containerId, fragment, fragmentTag)
	}
}