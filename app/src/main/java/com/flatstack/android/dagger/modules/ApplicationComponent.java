package com.flatstack.android.dagger.modules;

import com.flatstack.android.App;

import dagger.Component;

/**
 * Created by ilyaeremin on 11/16/14.
 */
@Component(modules = ApplicationScopeModule.class)
public interface ApplicationComponent {
    App getApp(App app);
}
