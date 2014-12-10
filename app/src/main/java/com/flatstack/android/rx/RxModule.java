package com.flatstack.android.rx;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 05/12/14
 */
@RequiredArgsConstructor(suppressConstructorProperties = true)
public abstract @Data class RxModule {
  protected final @NotNull RxActivity activity;
}
