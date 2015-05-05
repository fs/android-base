package com.flatstack.android.dagger.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.flatstack.android.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IlyaEremin on 13/04/15.
 */
@Module
public class MainModule {

    private final FragmentActivity activity;

    public MainModule(FragmentActivity activity) {
        this.activity = activity;
    }

    public MainModule(Fragment fragment) {
        this.activity = fragment.getActivity();
    }

    @Provides @ActivityScope FragmentActivity providesActivity() {
        return activity;
    }

}
