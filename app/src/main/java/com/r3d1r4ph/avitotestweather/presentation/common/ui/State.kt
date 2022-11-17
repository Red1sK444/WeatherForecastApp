package com.r3d1r4ph.avitotestweather.presentation.common.ui

interface State

internal val emptyState: State
    get() = object : State {}
