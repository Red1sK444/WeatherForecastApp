package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.dpToPx

class HourlyForecastItemDecoration : RecyclerView.ItemDecoration() {

	private val margin16 = 16.dpToPx
	private val margin6 = 6.dpToPx

	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	) {
		super.getItemOffsets(outRect, view, parent, state)

		val position = parent.getChildAdapterPosition(view)
		if (position == RecyclerView.NO_POSITION) return

		outRect.apply {
			top = margin16
			bottom = margin16
			left = margin6
			right = margin6

			when (position) {
				0                   -> left = margin16
				state.itemCount - 1 -> right = margin16
			}
		}
	}
}