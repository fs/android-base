package com.flatstack.android.utils;

import android.app.Activity;
import android.os.Build;
import com.flatstack.android.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class StatusBar {
  public static void setup(@NotNull Activity activity) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      final int top = new SystemBarTintManager(activity).getConfig().getPixelInsetTop(false);
      Views.setHeight(activity.findViewById(R.id.statusbar), top);
    }
  }
}
