package com.flatstack.android.util

import android.content.Context
import androidx.annotation.StringRes

class StringResource(private val context: Context) {
    fun getString(@StringRes stringRes: Int) = context.getString(stringRes)
}
