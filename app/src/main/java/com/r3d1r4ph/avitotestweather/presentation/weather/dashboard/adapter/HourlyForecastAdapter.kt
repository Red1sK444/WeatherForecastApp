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
import com.r3d1r4ph.truedomain.weather.forecast.model.hourly.forecast.HourlyForecast
import java.text.SimpleDateFormat
import java.util.*

class HourlyForecastAdapter : ListAdapter<HourlyForecast, HourlyForecastAdapter.HourlyForecastViewHolder>(DIFF) {

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

		fun bind(hourlyForecast: HourlyForecast) {
			with(binding) {
				itemHourlyForecastTimeTextView.text = SimpleDateFormat("HH:mm", Locale("RU")).format(Date(hourlyForecast.dateTime * 1000))
				itemHourlyForecastWeatherIconImageView.load(hourlyForecast.weather.icon)
				itemHourlyForecastTemperatureTextView.text = root.context.getString(R.string.dashboard_celsius, hourlyForecast.main.temperature.toInt())
			}
		}
	}

	private companion object {

		val DIFF = createDiff<HourlyForecast> { oldItem, newItem ->
			oldItem.dateTime == newItem.dateTime
		}
	}
}