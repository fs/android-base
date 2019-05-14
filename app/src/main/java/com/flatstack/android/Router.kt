package com.flatstack.android

import android.content.Context
import android.content.Intent
import com.flatstack.android.login.LoginActivity
import com.flatstack.android.profile.ProfileActivity

class Router(
    private val appContext: Context
) {
    fun login(context: Context = appContext, clearStack: Boolean = false) {
        context.startActivity(Intent(context, LoginActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }

    fun profile(context: Context, clearStack: Boolean = false) {
        context.startActivity(Intent(context, ProfileActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }
}
