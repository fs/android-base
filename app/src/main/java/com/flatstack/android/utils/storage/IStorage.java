package com.flatstack.android.utils.storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ereminilya on 8/1/17.
 */

public interface IStorage {

    @Nullable <T> T get(@NonNull String key, @NonNull Type type);

    <T> void put(@NonNull String key, @NonNull T item);

    void putString(@NonNull final String key, @NonNull String str);

    @Nullable String getString(@NonNull String key);

    void putLong(@NonNull String key, long number);

    long getLong(@NonNull String key, long defaultValue);

    void putInt(@NonNull String key, int number);

    int getInt(@NonNull String key, int defaultValue);

    void putBoolean(@NonNull String key, boolean value);

    boolean getBoolean(@NonNull String key, boolean defaultValue);

    void remove(@NonNull String key);

    <T> void putCollection(@NonNull String key, @NonNull List<T> items);

    @Nullable <T> List<T> getCollection(@NonNull String key, @NonNull Type type);
}
