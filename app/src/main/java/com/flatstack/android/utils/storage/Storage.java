package com.flatstack.android.utils.storage;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ereminilya on 8/1/17.
 */

public class Storage implements IStorage {

    private final SharedPreferences sp;
    private final Gson gson;

    public Storage(SharedPreferences sp, Gson gson) {
        this.sp = sp;
        this.gson = gson;
    }

    @Override
    @Nullable public <T> T get(@NonNull String key, @NonNull Type type) {
        String json = sp.getString(key, "");
        if ("".equals(json)) return null;
        else {
            return gson.fromJson(json, type);
        }
    }

    @Override public void put(@NonNull String key, @NonNull Object items) {
        sp.edit().putString(key, gson.toJson(items)).apply();
    }

    @Override public void putString(@NonNull String key, @NonNull String str) {
        sp.edit().putString(key, str).apply();
    }

    @Nullable @Override public String getString(@NonNull String key) {
        return sp.getString(key, null);
    }

    @Override public void putLong(@NonNull String key, long number) {
        sp.edit().putLong(key, number).apply();
    }

    @Override public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @Override public void putInt(@NonNull String key, int number) {
        sp.edit().putInt(key, number).apply();
    }

    @Override public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    @Override public void putBoolean(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    @Override public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    @Override public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    public <T> void putCollection(@NonNull String key, @NonNull List<T> items) {
        put(key, items);
    }

    @Override public <T> List<T> getCollection(@NonNull String key, @NonNull Type type) {
        return get(key, type);
    }

}