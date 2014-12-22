package com.flatstack.android;

import com.flatstack.android.utils.Lists;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 22/12/14
 */
public class TestApp extends App {
  @NotNull @Override protected List<Object> getDaggerModules() {
    return Lists.add(super.getDaggerModules(), new TestModule());
  }
}
