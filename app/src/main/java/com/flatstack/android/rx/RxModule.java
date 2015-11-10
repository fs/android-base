package com.flatstack.android.rx;

import android.support.annotation.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by adelnizamutdinov on 05/12/14
 */
@RequiredArgsConstructor(suppressConstructorProperties = true)
public abstract @Data class RxModule {
    protected final @NonNull RxActivity activity;
}
