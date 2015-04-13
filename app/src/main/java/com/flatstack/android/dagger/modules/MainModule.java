package com.flatstack.android.dagger.modules;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IlyaEremin on 13/04/15.
 */
@Module
public class MainModule {
    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
            .downloader(new OkHttpDownloader(okHttpClient))
            .build();
    }
}
