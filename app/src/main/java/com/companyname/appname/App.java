package com.companyname.appname;

import android.app.Application;

import com.companyname.appname.dagger.AppScopeDaggerModule;
import com.crittercism.app.Crittercism;

import org.jetbrains.annotations.NotNull;

import dagger.ObjectGraph;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class App extends Application {
    @NotNull @Getter ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        Crittercism.initialize(getApplicationContext(), getString(R.string.crittercism_app_id));
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportingTree());
        objectGraph = ObjectGraph.create(new AppScopeDaggerModule(this));
    }
}
