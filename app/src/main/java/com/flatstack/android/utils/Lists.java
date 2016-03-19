package com.flatstack.android.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adel on 6/7/14
 */
public class Lists {
  @SafeVarargs @NonNull public static <T> List<T> mutableOf(@NonNull T... objects) {
    List<T> list = new ArrayList<>(objects.length);
    Collections.addAll(list, objects);
    return list;
  }

  @SafeVarargs @NonNull public static <T> List<T> add(@NonNull List<T> list,
                                                      @NonNull T... objects) {
    Collections.addAll(list, objects);
    return list;
  }
}
