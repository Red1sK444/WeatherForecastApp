package com.r3d1r4ph.avitotestweather.presentation.weather.search.city.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.databinding.ItemRecyclerCityBinding
import com.r3d1r4ph.avitotestweather.presentation.common.adapter.createDiff
import com.r3d1r4ph.domain.cities.model.City

class CityAdapter(private val listener: CityListener) : ListAdapter<City, CityAdapter.CityViewHolder>(DIFF) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
		CityViewHolder(
			LayoutInflater
				.from(parent.context)
				.inflate(R.layout.item_recycler_city, parent, false)
		)

	override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	inner class CityViewHolder(view: View) : ViewHolder(view) {

		private val binding = ItemRecyclerCityBinding.bind(view)

		init {
			binding.root.setOnClickListener {
				listener.onCityClick(getItem(bindingAdapterPosition))
			}
		}

		fun bind(city: City) {
			binding.itemCityCompleteNameTextView.text =
				binding.root.resources.getString(R.string.search_city_complete_city_name, city.name, city.region, city.country)
		}
	}

	fun interface CityListener {

		fun onCityClick(city: City)
	}

	private companion object {

		val DIFF = createDiff<City> { oldItem, newItem ->
			oldItem.id == newItem.id
		}
	}
}