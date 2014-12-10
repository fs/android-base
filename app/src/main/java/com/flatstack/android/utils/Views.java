package com.flatstack.android.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 04/12/14
 */
public class Views {
  public static @NotNull View root(@NotNull Activity activity) {
    return activity.findViewById(android.R.id.content);
  }

  public static void setHeight(@NotNull View view, int height) {
    final ViewGroup.LayoutParams params = view.getLayoutParams();
    if (params != null) {
      params.height = height;
      view.setLayoutParams(params);
    }
  }
}
