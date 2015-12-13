package com.flatstack.android;

import com.flatstack.android.dagger.components.AppComponent;
import com.flatstack.android.dagger.components.DaggerAppComponent;
import com.flatstack.android.dagger.modules.TestModule;

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
