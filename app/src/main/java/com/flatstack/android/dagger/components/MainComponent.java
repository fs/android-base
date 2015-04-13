package com.flatstack.android.dagger.components;


import android.support.v4.app.FragmentActivity;

import com.flatstack.android.MainActivity;
import com.flatstack.android.dagger.ActivityScope;
import com.flatstack.android.dagger.modules.MainModule;
import com.flatstack.android.fragments.MainFragment;

import dagger.Component;

/**
 * Created by IlyaEremin on 13/04/15.
 */
@ActivityScope
@Component(
    dependencies = {
        AppComponent.class
    },
    modules = {
        MainModule.class
    }
)
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(MainFragment fragment);

    FragmentActivity activity();
}
