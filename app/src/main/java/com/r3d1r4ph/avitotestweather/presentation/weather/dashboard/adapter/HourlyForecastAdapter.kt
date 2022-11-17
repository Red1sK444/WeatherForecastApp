package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.databinding.ItemRecyclerHourlyForecastBinding
import com.r3d1r4ph.avitotestweather.presentation.common.adapter.createDiff
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.HourlyForecastUi

class HourlyForecastAdapter : ListAdapter<HourlyForecastUi, HourlyForecastAdapter.HourlyForecastViewHolder>(DIFF) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder =
		HourlyForecastViewHolder(
			LayoutInflater
				.from(parent.context)
				.inflate(R.layout.item_recycler_hourly_forecast, parent, false)
		)

	override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class HourlyForecastViewHolder(view: View) : ViewHolder(view) {

		private val binding = ItemRecyclerHourlyForecastBinding.bind(view)

		fun bind(hourlyForecastUi: HourlyForecastUi) {
			with(binding) {
				itemHourlyForecastTimeTextView.text = hourlyForecastUi.formattedTime
				itemHourlyForecastWeatherIconImageView.load(hourlyForecastUi.weatherIconUrl)
				itemHourlyForecastTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, hourlyForecastUi.temperature)
			}
		}
	}

	private companion object {

		val DIFF = createDiff<HourlyForecastUi> { oldItem, newItem ->
			oldItem.formattedTime == newItem.formattedTime
		}
	}
}