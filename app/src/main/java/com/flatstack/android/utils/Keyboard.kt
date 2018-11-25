package com.flatstack.android.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Keyboard {

    fun hide(activity: Activity) {
        val inputManager = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = activity.currentFocus
        if (v != null) {
            inputManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun hide(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!imm.isActive) {
            return
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun show(context: Context) {
        val imm = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun show(view: View) {
        val inputManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
