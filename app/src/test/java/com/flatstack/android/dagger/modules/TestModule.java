package com.flatstack.android.dagger.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import dagger.Module;

/**
 * Created by adelnizamutdinov on 22/12/14
 */
@Module
public class TestModule extends AppModule {

    public TestModule(@NonNull Application application) {
        super(application);
    }
}
