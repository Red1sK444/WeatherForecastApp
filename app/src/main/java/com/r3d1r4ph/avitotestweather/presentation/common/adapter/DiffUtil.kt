package com.r3d1r4ph.avitotestweather.presentation.common.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

fun <T : Any> createDiff(areItemsTheSame: (oldItem: T, newItem: T) -> Boolean): DiffUtil.ItemCallback<T> =
	object : DiffUtil.ItemCallback<T>() {
		override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
			areItemsTheSame.invoke(oldItem, newItem)

		@SuppressLint("DiffUtilEquals")
		override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
			return oldItem == newItem
		}
	}