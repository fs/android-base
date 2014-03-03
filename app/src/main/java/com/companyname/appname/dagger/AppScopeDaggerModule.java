package com.companyname.appname.dagger;

import android.content.Context;
import android.os.Environment;

import com.companyname.appname.qualifiers.CacheDir;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Module(library = true)
public class AppScopeDaggerModule {
    final Context context;

    public AppScopeDaggerModule(Context context) {this.context = context;}

    @Provides Context provideContext() {return context;}

    @Provides @CacheDir File provideCacheDir(Context context) {
        boolean mounted = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        return mounted ? context.getExternalCacheDir() : context.getCacheDir();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(@CacheDir File cacheDir) {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            okHttpClient.setResponseCache(new HttpResponseCache(cacheDir, 20 * 1024 * 1024));
        } catch (IOException ignored) {
        }
        return okHttpClient;
    }

    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }
}
