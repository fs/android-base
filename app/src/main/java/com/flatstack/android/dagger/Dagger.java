package com.flatstack.android.dagger;

import android.app.Fragment;
import android.content.Context;
import android.view.View;

import org.jetbrains.annotations.NotNull;


public class Dagger {
    // TODO dagger
//    @NotNull public static ObjectGraph getObjectGraph(@NotNull Context context) {
//        if (context instanceof Injector) {
//            return ((Injector) context).getObjectGraph();
//        }
//        throw new IllegalArgumentException(String.format(
//                "Your %s should implement Injector interface",
//                context.getClass().getSimpleName()));
//    }

    public static void inject(@NotNull Context context) {
        // TODO dagger
//     getObjectGraph(context).inject(context);
    }

    public static void inject(@NotNull View view) {
        // TODO dagger
//        getObjectGraph(view.getContext()).inject(view);
    }

    public static void inject(@NotNull Fragment fragment) {
        // TODO dagger
//        getObjectGraph(fragment.getActivity()).inject(fragment);
    }

    public static void inject(@NotNull ScopedFragment fragment) {
        // TODO dagger
//        getObjectGraph(fragment.getScopedContext()).inject(fragment);
    }
}