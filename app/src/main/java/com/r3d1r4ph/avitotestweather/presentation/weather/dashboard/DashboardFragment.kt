package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.databinding.FragmentDashboardBinding
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.getParentAsListener
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.showErrorSnack
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolder
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolderImpl
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils
import com.r3d1r4ph.avitotestweather.presentation.common.utils.DateFormattingUtils.formatByPattern
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.adapter.DailyForecastAdapter
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.adapter.HourlyForecastAdapter
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.decoration.DailyForecastItemDecoration
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.decoration.HourlyForecastItemDecoration
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardAction
import com.r3d1r4ph.avitotestweather.presentation.weather.dashboard.model.DashboardState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), ViewBindingHolder<FragmentDashboardBinding> by ViewBindingHolderImpl() {

	private val fragmentListener by lazy { getParentAsListener<DashboardFragmentListener>() }
	private val viewModel by viewModel<DashboardViewModel>()
	private var hourlyForecastAdapter: HourlyForecastAdapter? = null
	private var dailyForecastAdapter: DailyForecastAdapter? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentDashboardBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()
		initObservers()
	}

	override fun onResume() {
		super.onResume()
		viewModel.fetchWeatherForecast()
	}

	private fun initView() {
		hourlyForecastAdapter = HourlyForecastAdapter()
		dailyForecastAdapter = DailyForecastAdapter()

		withBinding {
			dashboardHourlyForecastRecyclerView.apply {
				adapter = hourlyForecastAdapter
				addItemDecoration(HourlyForecastItemDecoration())
			}
			dashboardDailyForecastRecyclerView.apply {
				adapter = dailyForecastAdapter
				addItemDecoration(DailyForecastItemDecoration())
			}
			dashboardSearchCityImageButton.setOnClickListener { viewModel.onSearchCityClick() }
		}
	}

	private fun initObservers() {
		viewModel.state.observe(viewLifecycleOwner) { state -> handleState(state) }
		viewModel.event.observe(viewLifecycleOwner) { event ->
			event.getContentIfNotHandled()?.let { handleAction(it) }
		}
	}

	private fun handleState(state: DashboardState) {
		val currentWeather = state.currentWeather ?: return
		with(binding) {
			dashboardCityTextView.text = getString(R.string.dashboard_city_country, currentWeather.city, currentWeather.country)
			dashboardLastUpdateTimeTextView.text = getString(R.string.dashboard_actual_on, currentWeather.dateTime.formatByPattern())
			dashboardCurrentTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeather.main.temperature.toInt())
			dashboardWeatherStateTextView.text = currentWeather.weather.description.replaceFirstChar { it.uppercaseChar() }
			dashboardMinTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeather.main.minTemperature.toInt())
			dashboardMaxTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeather.main.maxTemperature.toInt())
			dashboardHumidityTextView.text = getString(R.string.dashboard_humidity, currentWeather.main.humidity)
			dashboardFeelsLikeTemperatureTextView.text = getString(R.string.dashboard_feels_like, currentWeather.main.feelsLike.toInt())
			hourlyForecastAdapter?.submitList(state.hourlyForecasts)
			dailyForecastAdapter?.submitList(state.dailyForecasts)
		}
	}

	private fun handleAction(action: DashboardAction) {
		when (action) {
			DashboardAction.ShowError -> showErrorSnack()
			DashboardAction.OpenSearchCityScreen -> fragmentListener.onSearchCityClick()
		}
	}

	override fun onDestroyView() {
		hourlyForecastAdapter = null
		dailyForecastAdapter = null
		super.onDestroyView()
	}

	fun interface DashboardFragmentListener {

		fun onSearchCityClick()
	}

	companion object {

		val TAG: String = DashboardFragment::class.java.simpleName

		fun newInstance() = DashboardFragment()
	}
}