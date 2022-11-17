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
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DailyForecastUi

class DailyForecastAdapter : ListAdapter<DailyForecastUi, DailyForecastAdapter.DailyForecastViewHolder>(DIFF) {

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

		fun bind(dailyForecastUi: DailyForecastUi) {
			with(binding) {
				itemDailyForecastDateTextView.text = dailyForecastUi.formattedDateTime
				itemDailyForecastWeatherIconImageView.load(dailyForecastUi.weatherIconUrl)
				itemDailyForecastTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecastUi.temperature)
				itemDailyForecastMinTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecastUi.minTemperature)
				itemDailyForecastMaxTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, dailyForecastUi.maxTemperature)
			}
		}
	}

	private companion object {

		val DIFF = createDiff<DailyForecastUi> { oldItem, newItem ->
			oldItem.formattedDateTime == newItem.formattedDateTime
		}
	}
}