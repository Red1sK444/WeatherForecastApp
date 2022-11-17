package com.r3d1r4ph.avitotestweather.presentation.common.ui

class Event<T>(private val content: T) {
    private var isHandled = false

    fun getContentIfNotHandled(): T? = if (isHandled) null else content.also { isHandled = true }

    fun peekContent(): T = content
}
