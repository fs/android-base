package com.flatstack.android.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.flatstack.android.App;
import com.flatstack.android.qualifiers.CacheDir;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    final @NonNull Application application;

    public AppModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides App provideApplication() {
        return (App) application;
    }

    @Provides Context provideContext() {
        return application;
    }

    @Provides @CacheDir File provideCacheDir(Context context) {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
            ? context.getExternalCacheDir()
            : context.getCacheDir();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(@CacheDir File cacheDir) {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            okHttpClient.setCache(new Cache(cacheDir, 20 * 1024 * 1024));
        } catch (IOException ignored) {
        }
        return okHttpClient;
    }

    @Provides @Singleton Gson provideJackson() {
        return new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
            .downloader(new OkHttpDownloader(okHttpClient))
            .build();
    }

}
