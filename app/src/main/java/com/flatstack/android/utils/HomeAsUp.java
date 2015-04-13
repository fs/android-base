package com.flatstack.android.utils;

import android.support.v7.app.ActionBar;
import com.flatstack.android.rx.RxActivity;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class HomeAsUp {
  public static void enable(@NotNull RxActivity activity) {
    final ActionBar bar = activity.getSupportActionBar();
    if (bar != null) {
      bar.setDisplayHomeAsUpEnabled(true);
    }
  }

  public static void disable(@NotNull RxActivity activity) {
    final ActionBar bar = activity.getSupportActionBar();
    if (bar != null) {
      bar.setHomeButtonEnabled(false);
      bar.setDisplayHomeAsUpEnabled(false);
    }
  }
}
