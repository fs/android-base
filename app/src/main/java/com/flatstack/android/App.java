package com.flatstack.android;

import android.app.Application;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.dagger.modules.ApplicationScopeModule;
import com.flatstack.android.utils.Lists;
import com.flatstack.android.utils.TimberCrashReportingTree;
import dagger.ObjectGraph;
import java.util.List;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class App extends Application implements Injector {
  @NotNull @Getter final ObjectGraph objectGraph = ObjectGraph.create(getDaggerModules().toArray());

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
