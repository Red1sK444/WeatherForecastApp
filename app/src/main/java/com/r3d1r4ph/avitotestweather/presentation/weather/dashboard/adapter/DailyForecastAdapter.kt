package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.databinding.ItemRecycerDailyForecastBinding
import com.r3d1r4ph.avitotestweather.presentation.common.adapter.createDiff
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils.formatByPattern
import com.r3d1r4ph.truedomain.weather.forecast.model.daily.forecast.DailyForecast

class DailyForecastAdapter : ListAdapter<DailyForecast, DailyForecastAdapter.DailyForecastViewHolder>(DIFF) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder =
		DailyForecastViewHolder(
			LayoutInflater
				.from(parent.context)
				.inflate(R.layout.item_recycer_daily_forecast, parent, false)
		)

	override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class DailyForecastViewHolder(view: View) : ViewHolder(view) {

		private val binding = ItemRecycerDailyForecastBinding.bind(view)

		fun bind(dailyForecast: DailyForecast) {
			with(binding) {
				itemDailyForecastDateTextView.text = dailyForecast.dateTime.formatByPattern(pattern = DateFormattingUtils.DateTimePattern.DAY_MONTH)
				itemDailyForecastWeatherIconImageView.load(dailyForecast.weather.icon)
				itemDailyForecastTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecast.temperature.day.toInt())
				itemDailyForecastMinTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecast.temperature.min.toInt())
				itemDailyForecastMaxTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecast.temperature.max.toInt())
			}
		}
	}

	private companion object {

		val DIFF = createDiff<DailyForecast> { oldItem, newItem ->
			oldItem.dateTime == newItem.dateTime
		}
	}
}