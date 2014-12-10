package com.flatstack.android.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatstack.android.qualifiers.CacheDir;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.Preferences;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.JacksonSerializer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AppDaggerModule {
    final @NotNull Application application;

    public AppDaggerModule(@NotNull Application application) {
        this.application = application;
    }

    @Provides Context provideContext() { return application; }

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

    /*
     *  Although Picasso is only needed in the UI scope, it subscribes to the network status updates
     *  and leaks the Activity's context, which is pretty bad
     */
    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

  @Provides @Singleton ObjectMapper provideJackson() { return new ObjectMapper(); }

  @Provides @Singleton Preferences providePreferences(Context context,
                                                      ObjectMapper objectMapper) {
    Esperandro.setSerializer(new JacksonSerializer(objectMapper));
    return Esperandro.getPreferences(Preferences.class, context);
  }

  @Provides @Singleton DatabaseHelper provideDatabaseHelper(Context context) {
    return new DatabaseHelper(context);
  }
}
