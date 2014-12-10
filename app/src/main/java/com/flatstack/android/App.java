package com.flatstack.android;

import android.app.Application;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.dagger.modules.AppDaggerModule;
import com.flatstack.android.utils.Lists;
import com.flatstack.android.utils.TimberCrashReportingTree;
import dagger.ObjectGraph;
import java.util.List;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class App extends Application implements Injector {
  @Getter @NotNull ObjectGraph objectGraph;

  @Override public void onCreate() {
    super.onCreate();
    Timber.plant(BuildConfig.DEBUG
                     ? new Timber.DebugTree()
                     : new TimberCrashReportingTree());
    objectGraph = ObjectGraph.create(getDaggerModules().toArray());
  }

  @NotNull protected List<Object> getDaggerModules() {
    return Lists.mutableOf(new AppDaggerModule(this));
  }
}
