package com.r3d1r4ph.avitotestweather.presentation.common.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this.map { it }