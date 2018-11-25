package com.flatstack.android.utils.ui

import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes

class ScreenConfig(@LayoutRes val layoutRes: Int,
                   val titleRes: Int = 0,
                   val title: String? = null,
                   val enableBackButton: Boolean = false,
                   @MenuRes val menuRes: Int = 0)