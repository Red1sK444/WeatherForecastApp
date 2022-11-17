package com.r3d1r4ph.avitotestweather.presentation.common.ui

import androidx.lifecycle.MutableLiveData
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.asLiveData

abstract class StatefulAppViewModel<S : State, A : Action>(defaultState: S) : AppViewModel<A>() {

	protected val _state = MutableLiveData(defaultState)
	val state = _state.asLiveData()

	protected fun updateState(reducer: S.() -> S) {
		val currentState = _state.value ?: return
		_state.postValue(currentState.reducer())
	}
}