package com.flatstack.android.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adelnizamutdinov on 04/12/14
 */
public class Views {

    @NonNull public static View root(@NonNull Activity activity) {
        return activity.findViewById(android.R.id.content);
    }

    public static void setHeight(@NonNull View view, int height) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            params.height = height;
            view.setLayoutParams(params);
        }
    }
}
