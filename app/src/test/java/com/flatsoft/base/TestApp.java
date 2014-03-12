package com.flatsoft.base;

import com.flatsoft.base.dagger.ModulesTestModule;
import com.flatsoft.base.dagger.UiScopeDaggerModule;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class TestApp extends App {
    @NotNull @Override protected List<Object> getModules() {
        List<Object> modules = super.getModules();
        modules.addAll(Arrays.asList(new ModulesTestModule(), new UiScopeDaggerModule()));
        return modules;
    }
}
