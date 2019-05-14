package com.flatstack.android.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Keyboard {

    fun hide(activity: Activity) {
        activity.currentFocus?.let {
            inputMethodManager(activity)
                ?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hide(view: View) {
        val inputMethod = inputMethodManager(view.context)
        inputMethod?.let {
            if (it.isActive) {
                it.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    fun show(context: Context) {
        inputMethodManager(context)
            ?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun show(view: View) {
        inputMethodManager(view.context)
            ?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun inputMethodManager(context: Context) =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
}
