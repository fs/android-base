package com.flatstack.android;

import android.app.Application;
import android.content.Context;

import com.flatstack.android.utils.TimberCrashReportingTree;

import timber.log.Timber;

public class App extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new TimberCrashReportingTree());
    }

}
