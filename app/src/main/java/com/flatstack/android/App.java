package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.dagger.components.AppComponent;
import com.flatstack.android.dagger.components.DaggerAppComponent;
import com.flatstack.android.dagger.modules.AppModule;
import com.flatstack.android.utils.Lists;
import com.flatstack.android.utils.TimberCrashReportingTree;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

public class App extends Application {

    public static AppComponent component;

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(BuildConfig.DEBUG
            ? new Timber.DebugTree()
            : new TimberCrashReportingTree());
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @NotNull protected List<Object> getDaggerModules() {
        return Lists.mutableOf(new AppModule(this));
    }

}
