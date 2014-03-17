package com.flatsoft.base.dagger;

import android.app.Fragment;
import android.content.Context;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import dagger.ObjectGraph;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class Dagger {
    @NotNull public static ObjectGraph getObjectGraph(Context context) {
        if (!(context instanceof Injector)) {
            throw new IllegalArgumentException("Your context should implement Injector interface");
        }
        return ((Injector) context).getObjectGraph();
    }

    public static void inject(Context context) {
        getObjectGraph(context).inject(context);
    }

    public static void inject(View view) {
        getObjectGraph(view.getContext()).inject(view);
    }

    public static void inject(Fragment fragment) {
        getObjectGraph(fragment.getActivity()).inject(fragment);
    }
}