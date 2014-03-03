package com.companyname.appname;

import com.companyname.appname.dagger.ModulesTestModule;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class TestApp extends App {
    @NotNull @Override protected List<Object> getModules() {
        List<Object> modules = super.getModules();
        modules.addAll(Arrays.asList(new ModulesTestModule()));
        return modules;
    }
}
