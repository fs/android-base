package com.flatsoft.base.dagger;

import org.jetbrains.annotations.NotNull;

import dagger.ObjectGraph;

public interface Injector {
    @NotNull ObjectGraph getObjectGraph();
}
