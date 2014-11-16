package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.dagger.modules.ApplicationComponent;
import com.flatstack.android.dagger.modules.ApplicationScopeModule;
import com.flatstack.android.dagger.modules.Dagger_ApplicationComponent;
import com.flatstack.android.utils.Lists;
import com.flatstack.android.utils.TimberCrashReportingTree;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO dagger
//import dagger.ObjectGraph;
import lombok.Getter;
import timber.log.Timber;

// TODO dagger
// implements Injector
public class App extends Application {

    private ApplicationComponent appComponent;

    // TODO dagger
//    @NotNull @Getter
//    final ObjectGraph objectGraph = ObjectGraph.create(getDaggerModules().toArray());

    @Override public void onCreate() {
        super.onCreate();

        appComponent = Dagger_ApplicationComponent.builder().applicationScopeModule(new ApplicationScopeModule(this)).build();

        Timber.plant(BuildConfig.DEBUG
                             ? new Timber.DebugTree()
                             : new TimberCrashReportingTree());
    }
//
//    @NotNull protected List<Object> getDaggerModules() {
//        return Lists.mutableOf(new ApplicationScopeModule(this));
//    }
}
