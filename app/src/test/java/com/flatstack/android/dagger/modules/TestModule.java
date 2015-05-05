package com.flatstack.android.dagger.modules;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

import dagger.Module;

/**
 * Created by adelnizamutdinov on 22/12/14
 */
@Module
public class TestModule extends AppModule {

    public TestModule(@NotNull Application application) {
        super(application);
    }
}
