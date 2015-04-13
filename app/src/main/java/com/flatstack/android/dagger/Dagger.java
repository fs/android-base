package com.flatstack.android.dagger;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import dagger.ObjectGraph;
import org.jetbrains.annotations.NotNull;

public class Dagger {
  @NotNull public static ObjectGraph getObjectGraph(@NotNull Context context) {
    if (context instanceof Injector) {
      return ((Injector) context).getObjectGraph();
    }
    throw new IllegalArgumentException(String.format(
        "Your %s should implement Injector interface",
        context.getClass().getSimpleName()));
  }

  public static void inject(@NotNull Context context) {
    getObjectGraph(context).inject(context);
  }

  public static void inject(@NotNull View view) {
    getObjectGraph(view.getContext()).inject(view);
  }

  public static void inject(@NotNull Fragment fragment) {
    getObjectGraph(fragment.getActivity()).inject(fragment);
  }

  public static void inject(@NotNull android.support.v4.app.Fragment fragment) {
    getObjectGraph(fragment.getActivity()).inject(fragment);
  }
}