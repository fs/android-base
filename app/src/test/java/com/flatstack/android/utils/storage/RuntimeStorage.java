package com.flatstack.android.utils.storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuntimeStorage implements IStorage {

    private Map<String, Object> map = new HashMap<>();

    @Override public <T> void put(@NonNull String key, @NonNull T item) {
        map.put(key, item);
    }

    @Nullable @Override
    public <T> T get(@NonNull String key, @NonNull Type clazz) {
        return (T) map.get(key);
    }

    @Override public void putLong(@NonNull String key, long number) {
        map.put(key, Long.valueOf(number));
    }

    @Override public long getLong(@NonNull String key, long defaultValue) {
        Object o = map.get(key);
        return o != null ? (long) o : defaultValue;
    }

    @Override public void putInt(@NonNull String key, int number) {
        map.put(key, Integer.valueOf(number));
    }

    @Override public int getInt(@NonNull String key, int defaultValue) {
        Object o = map.get(key);
        return o != null ? (int) o : defaultValue;
    }

    @Override public void putBoolean(@NonNull String key, boolean value) {
        map.put(key, Boolean.valueOf(value));
    }

    @Override public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        Object o = map.get(key);
        return o != null ? (boolean) o : defaultValue;
    }

    @Override public void putString(@NonNull String key, @NonNull String str) {
        map.put(key, str);
    }

    @Override public String getString(@NonNull String key) {
        Object o = map.get(key);
        return (o != null) ? (String) o : null;
    }

    @Override public void remove(@NonNull String key) {
        map.remove(key);
    }

    @Override public <T> void putCollection(@NonNull String key, @NonNull List<T> items) {
        map.put(key, items);
    }

    @Override public <T> List<T> getCollection(@NonNull String key, @NonNull Type type) {
        return (List<T>) map.get(key);
    }
}