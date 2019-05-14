package com.flatstack.android.util.ui

import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes

class ScreenConfig(
    @LayoutRes val layoutRes: Int,
    val titleRes: Int = 0,
    val title: String? = null,
    val enableBackButton: Boolean = false,
    @MenuRes val menuRes: Int = 0
)
