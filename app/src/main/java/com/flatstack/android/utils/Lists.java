package com.flatstack.android.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adel on 6/7/14
 */
public class Lists {
  @SafeVarargs @NotNull public static <T> List<T> mutableOf(@NotNull T... objects) {
    List<T> list = new ArrayList<>(objects.length);
    Collections.addAll(list, objects);
    return list;
  }

  @SafeVarargs @NotNull public static <T> List<T> add(@NotNull List<T> list,
                                                      @NotNull T... objects) {
    Collections.addAll(list, objects);
    return list;
  }
}
