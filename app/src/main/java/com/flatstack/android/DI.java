package com.flatstack.android;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by Ilya Eremin on 14.03.2016.
 */

/**
 * When you need different dependencies for tests and prod
 * then create new flavors: mock and producation and move these dependencies there
 */
public class DI {

    private static Context appContext;

    static {
        appContext = App.appContext;
    }

    public static File getCacheDir() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
            ? appContext.getExternalCacheDir()
            : appContext.getCacheDir();
    }

    private static volatile OkHttpClient okHttpInstance = null;

    public static OkHttpClient getHttpClient() {
        OkHttpClient localInstance = okHttpInstance;
        if (localInstance == null) {
            synchronized (OkHttpClient.class) {
                localInstance = okHttpInstance;
                if (localInstance == null) {
                    okHttpInstance = localInstance = new OkHttpClient.Builder()
                        .cache(new okhttp3.Cache(getCacheDir(), 20 * 1024 * 1024))
                        .build();
                }
            }
        }
        return localInstance;
    }

    private static volatile Picasso picassoInstance = null;

    public static Picasso getPicasso() {
        Picasso localInstance = picassoInstance;
        if (localInstance == null) {
            synchronized (Picasso.class) {
                localInstance = picassoInstance;
                if (localInstance == null) {
                    picassoInstance = localInstance = new Picasso.Builder(appContext)
                        .downloader(new OkHttp3Downloader(getHttpClient()))
                        .build();
                }
            }
        }
        return localInstance;
    }

}
