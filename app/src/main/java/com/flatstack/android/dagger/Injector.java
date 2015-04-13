package com.flatstack.android.dagger;

import dagger.ObjectGraph;
import org.jetbrains.annotations.NotNull;

public interface Injector {
  @NotNull ObjectGraph getObjectGraph();
}
