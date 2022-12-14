package com.r3d1r4ph.avitotestweather.presentation.weather.dashboard

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.r3d1r4ph.avitotestweather.R
import com.r3d1r4ph.avitotestweather.databinding.FragmentDashboardBinding
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.getParentAsListener
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.showErrorSnack
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolder
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolderImpl
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

	private val requestPermissionLauncher = registerForActivityResult(
		ActivityResultContracts.RequestPermission()
	) { isGranted -> viewModel.onRequestLocationPermissionResult(isGranted) }

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
			dashboardMyLocationImageButton.setOnClickListener { viewModel.onMyLocationClick() }
		}
	}

	private fun initObservers() {
		viewModel.state.observe(viewLifecycleOwner) { state -> handleState(state) }
		viewModel.event.observe(viewLifecycleOwner) { event ->
			event.getContentIfNotHandled()?.let { handleAction(it) }
		}
	}

	private fun handleState(state: DashboardState) {
		val currentWeatherUi = state.currentWeatherUi ?: return
		with(binding) {
			dashboardCityTextView.text = getString(R.string.dashboard_city_country, currentWeatherUi.city, currentWeatherUi.country)
			dashboardLastUpdateTimeTextView.text = getString(R.string.dashboard_actual_on, currentWeatherUi.formattedDateTime)
			dashboardCurrentTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeatherUi.temperature)
			dashboardWeatherStateTextView.text = currentWeatherUi.description
			dashboardMinTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeatherUi.minTemperature)
			dashboardMaxTemperatureTextView.text = getString(R.string.dashboard_celsius, currentWeatherUi.maxTemperature)
			dashboardHumidityTextView.text = getString(R.string.dashboard_humidity, currentWeatherUi.humidity)
			dashboardFeelsLikeTemperatureTextView.text = getString(R.string.dashboard_feels_like, currentWeatherUi.feelsLikeTemperature)
			hourlyForecastAdapter?.submitList(state.hourlyForecastUis)
			dailyForecastAdapter?.submitList(state.dailyForecastUis)
		}
	}

	private fun handleAction(action: DashboardAction) {
		when (action) {
			DashboardAction.ShowError                 -> showErrorSnack()
			DashboardAction.OpenSearchCityScreen      -> fragmentListener.onSearchCityClick()
			DashboardAction.RequestLocationPermission -> requestLocationPermission()
		}
	}

	private fun requestLocationPermission() {
		requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
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