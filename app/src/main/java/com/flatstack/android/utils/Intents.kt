package com.flatstack.android.utils

import android.app.Activity
import android.content.Intent

object Intents {

    fun isActivityExpandedFromLauncherIcon(activity: Activity): Boolean {
        return !activity.isTaskRoot && isActivityStartedFromLauncherIcon(activity.intent)
    }

    private fun isActivityStartedFromLauncherIcon(intent: Intent?): Boolean {
        if (intent != null) {
            return intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intent.action == Intent.ACTION_MAIN
        }
    }
}
