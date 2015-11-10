package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.dagger.components.AppComponent;
import com.flatstack.android.dagger.components.DaggerAppComponent;
import com.flatstack.android.dagger.modules.AppModule;
import com.flatstack.android.utils.TimberCrashReportingTree;

import timber.log.Timber;

public class App extends Application {

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new TimberCrashReportingTree());
        component = getDaggerComponent();
    }

    public AppComponent getDaggerComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return component;
    }

}
