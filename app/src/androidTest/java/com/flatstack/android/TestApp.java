package com.flatstack.android;

import com.flatstack.android.dagger.ModulesTestModule;
import com.flatstack.android.utils.Lists;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestApp extends App {
    @NotNull @Override protected List<Object> getDaggerModules() {
        return Lists.add(super.getDaggerModules(), new ModulesTestModule());
    }
}
