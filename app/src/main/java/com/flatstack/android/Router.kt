package com.flatstack.android

import android.content.Context
import android.content.Intent
import com.flatstack.android.login.LoginActivity
import com.flatstack.android.profile.ProfileActivity
import com.flatstack.android.registration.RegistrationActivity

class Router(
    private val appContext: Context
) {
    fun login(context: Context = appContext, shouldClearStack: Boolean = false) {
        context.startActivity(Intent(context, LoginActivity::class.java).apply {
            if (shouldClearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }

    fun profile(context: Context, shouldClearStack: Boolean = false) {
        context.startActivity(Intent(context, ProfileActivity::class.java).apply {
            if (shouldClearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }

    fun registration(context: Context, shouldClearStack: Boolean = false) {
        context.startActivity(Intent(context, RegistrationActivity::class.java).apply {
            if (shouldClearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }
}
