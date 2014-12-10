package com.flatstack.android.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import org.jetbrains.annotations.NotNull;

import rx.functions.Action1;

/**
 * Created by IlyaEremin on 09/12/14.
 */
public class ActionBars {
    public static void configure(@NotNull ActionBarActivity activity, @NotNull Action1<ActionBar> action) {
        action.call(activity.getSupportActionBar());
    }

    public static void configure(@NotNull Fragment fragment, @NotNull Action1<ActionBar> action) {
        configure((ActionBarActivity)fragment.getActivity(), action);
    }

    public static void homeAsUp(@NotNull ActionBar actionBar, boolean enabled) {
        actionBar.setHomeButtonEnabled(enabled);
        actionBar.setDisplayHomeAsUpEnabled(enabled);
    }
}
