package com.flatstack.android.utils;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.flatstack.android.rx.RxActivity;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class HomeAsUp {
    public static void enable(@NonNull RxActivity activity) {
        final ActionBar bar = activity.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static void disable(@NonNull RxActivity activity) {
        final ActionBar bar = activity.getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(false);
            bar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
