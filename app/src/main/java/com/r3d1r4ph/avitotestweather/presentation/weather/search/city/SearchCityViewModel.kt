package com.r3d1r4ph.avitotestweather.presentation.weather.search.city

import androidx.lifecycle.viewModelScope
import com.r3d1r4ph.avitotestweather.presentation.common.ui.StatefulAppViewModel
import com.r3d1r4ph.avitotestweather.presentation.common.utils.CoroutineUtils.debounce
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model.SearchCityAction
import com.r3d1r4ph.avitotestweather.presentation.weather.search.city.model.SearchCityState
import com.r3d1r4ph.domain.cities.GetCitiesUseCase
import com.r3d1r4ph.domain.cities.SaveSelectedCityUseCase
import com.r3d1r4ph.domain.cities.model.City
import com.r3d1r4ph.domain.cities.model.CityPrefix
import kotlinx.coroutines.launch

class SearchCityViewModel(
	private val getCitiesUseCase: GetCitiesUseCase,
	private val saveSelectedCityUseCase: SaveSelectedCityUseCase
) : StatefulAppViewModel<SearchCityState, SearchCityAction>(defaultState = SearchCityState(cities = emptyList(), loading = false)) {

	private val getCitiesByCityPrefixDebounce = debounce<String>(
		waitMs = DEFAULT_GET_CITIES_WAIT_TIME,
		scope = viewModelScope
	) { cityPrefix ->
		updateState {
			copy(
				loading = true
			)
		}
		getCitiesUseCase(param = CityPrefix(cityPrefix))
			.onSuccess {
				updateState {
					copy(
						cities = it,
						loading = false
					)
				}
			}
			.onFailure {
				updateState {
					copy(
						loading = false
					)
				}
				_event.postEvent(SearchCityAction.ShowError)
			}
	}

	fun getCitiesByCityPrefix(cityPrefix: String) {
		getCitiesByCityPrefixDebounce(cityPrefix)
	}

	fun onCitySelected(city: City) {
		viewModelScope.launch {
			saveSelectedCityUseCase(city)
				.onSuccess {
					_event.postEvent(SearchCityAction.OpenDashboardScreen)
				}
				.onFailure {
					_event.postEvent(SearchCityAction.ShowError)
				}
		}
	}

	fun onCityClick(city: City) {
		_event.postEvent(SearchCityAction.SelectCity(city))
	}

	private companion object {

		const val DEFAULT_GET_CITIES_WAIT_TIME = 1100L
	}
}