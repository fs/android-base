package com.flatsoft.base;

import android.app.Application;

import com.flatsoft.base.dagger.ApplicationScopeModule;
import com.flatsoft.base.dagger.Injector;
import com.flatsoft.base.utils.Lists;
import com.flatsoft.base.utils.TimberCrashReportingTree;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dagger.ObjectGraph;
import lombok.Getter;
import timber.log.Timber;

public class App extends Application implements Injector {
    final @NotNull @Getter ObjectGraph objectGraph = ObjectGraph.create(
            getDaggerModules().toArray());

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new TimberCrashReportingTree());
    }

    @NotNull protected List<Object> getDaggerModules() {
        return Lists.mutableOf(new ApplicationScopeModule(this));
    }
}
