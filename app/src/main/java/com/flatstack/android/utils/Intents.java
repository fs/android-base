package com.flatstack.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by almaziskhakov on 22/11/2017.
 */

public class Intents {

    public static boolean isActivityExpandedFromLauncherIcon(@NonNull Activity activity) {
        return !activity.isTaskRoot() && isActivityStartedFromLauncherIcon(activity.getIntent());
    }

    public static boolean isActivityStartedFromLauncherIcon(@Nullable Intent intent) {
        return intent != null &&
                intent.hasCategory(Intent.CATEGORY_LAUNCHER) &&
                intent.getAction() != null &&
                intent.getAction().equals(Intent.ACTION_MAIN);
    }

}