package com.r3d1r4ph.avitotestweather.presentation.common.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r3d1r4ph.avitotestweather.presentation.common.extensions.asLiveData

abstract class AppViewModel<A : Action> : ViewModel() {

	protected val _event = MutableLiveData<Event<A>>()
	val event = _event.asLiveData()

	protected fun MutableLiveData<Event<A>>.postEvent(action: A) {
		_event.postValue(Event(action))
	}
}
