package com.flatstack.android;

import com.flatstack.android.dagger.components.AppComponent;
import com.flatstack.android.dagger.components.DaggerAppComponent;
import com.flatstack.android.dagger.modules.AppModule;
import com.flatstack.android.dagger.modules.TestModule;
import com.flatstack.android.utils.Lists;

import java.util.List;

import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 22/12/14
 */
public class TestApp extends App {

    @Override
    public AppComponent getDaggerComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder().appModule(new TestModule(this)).build();
        }
        return component;
    }
}
