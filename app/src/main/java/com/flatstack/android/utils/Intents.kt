package com.flatstack.android.utils

import android.app.Activity
import android.content.Intent

object Intents {

    fun isActivityExpandedFromLauncherIcon(activity: Activity): Boolean {
        return !activity.isTaskRoot && isActivityStartedFromLauncherIcon(activity.intent)
    }

    private fun isActivityStartedFromLauncherIcon(intent: Intent?): Boolean {
        return intent != null &&
                intent.hasCategory(Intent.CATEGORY_LAUNCHER) &&
                intent.action != null &&
                intent.action == Intent.ACTION_MAIN
    }

}