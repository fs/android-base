package com.flatsoft.base;

import com.flatsoft.base.dagger.ModulesTestModule;
import com.flatsoft.base.utils.Lists;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestApp extends App {
    @NotNull @Override protected List<Object> getDaggerModules() {
        return Lists.add(super.getDaggerModules(), new ModulesTestModule());
    }
}
