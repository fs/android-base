package com.flatsoft.base;

import com.flatsoft.base.dagger.ModulesTestModule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestApp extends App {
    @NotNull @Override protected List<Object> getDaggerModules() {
        List<Object> modules = super.getDaggerModules();
        modules.add(new ModulesTestModule());
        return modules;
    }
}
