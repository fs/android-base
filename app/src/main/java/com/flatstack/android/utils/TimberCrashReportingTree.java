package com.flatstack.android.utils;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

public class TimberCrashReportingTree extends Timber.HollowTree {
    @Override public void e(Throwable t, String message, Object... args) {
        Crashlytics.logException(t);
    }
}
