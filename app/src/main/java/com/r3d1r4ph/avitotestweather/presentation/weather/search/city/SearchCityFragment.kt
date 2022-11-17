package com.r3d1r4ph.avitotestweather.presentation.weather.search.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.r3d1r4ph.avitotestweather.databinding.FragmentSearchCityBinding
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.getParentAsListener
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.showErrorSnack
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolder
import com.r3d1r4ph.avitotestweather.presentation.common.ui.ViewBindingHolderImpl
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.adapter.CityAdapter
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model.SearchCityAction
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model.SearchCityState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityFragment : Fragment(), ViewBindingHolder<FragmentSearchCityBinding> by ViewBindingHolderImpl() {

	private val fragmentListener by lazy { getParentAsListener<SearchCityFragmentListener>() }
	private val viewModel by viewModel<SearchCityViewModel>()
	private var cityAdapter: CityAdapter? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentSearchCityBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()
		initObservers()
	}

	private fun initView() {
		withBinding {
			searchCitySearchView.apply {
				setIconifiedByDefault(false)
				isIconified = false

				setOnQueryTextListener(
					object : SearchView.OnQueryTextListener {
						override fun onQueryTextSubmit(query: String?): Boolean {
							if (query != null && query.isNotBlank() && query.length > 2) {
								viewModel.getCitiesByCityPrefix(cityPrefix = query)
							}
							return false
						}

						override fun onQueryTextChange(newText: String?): Boolean {
							if (newText != null && newText.isNotBlank() && newText.length > 2) {
								viewModel.getCitiesByCityPrefix(cityPrefix = newText)
							}
							return true
						}
					}
				)

				cityAdapter = CityAdapter { city -> viewModel.onCityClick(city) }
				searchCityCitiesRecyclerView.adapter = cityAdapter
			}
		}
	}

	private fun initObservers() {
		viewModel.state.observe(viewLifecycleOwner) { state -> handleState(state) }
		viewModel.event.observe(viewLifecycleOwner) { event ->
			event.getContentIfNotHandled()?.let { action -> handleAction(action) }
		}
	}

	private fun handleState(state: SearchCityState) {
		withBinding {
			searchCityLoadingProgressBar.isVisible = state.loading
			searchCityNoResultsCardView.isVisible = state.cities.isEmpty()
		}
		cityAdapter?.submitList(state.cities)
	}

	private fun handleAction(action: SearchCityAction) {
		when (action) {
			is SearchCityAction.SelectCity -> viewModel.onCitySelected(action.city)
			SearchCityAction.OpenDashboardScreen -> fragmentListener.onCitySelected()
			SearchCityAction.ShowError -> showErrorSnack()
		}
	}

	override fun onDestroyView() {
		cityAdapter = null
		super.onDestroyView()
	}

	fun interface SearchCityFragmentListener {

		fun onCitySelected()
	}

	companion object {

		val TAG: String = SearchCityFragment::class.java.simpleName

		fun newInstance() = SearchCityFragment()
	}
}