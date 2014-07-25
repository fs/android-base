package com.flatstack.android.dagger;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.flatstack.android.R;
import com.flatstack.android.qualifiers.CacheDir;
import com.flatstack.android.utils.MixPanel;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationScopeModule {
    final Application application;

    public ApplicationScopeModule(Application application) {
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
        } catch (IOException ignored) { }
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

    @Provides @Singleton MixpanelAPI provideMixpanelAPI(Context context) {
        return MixpanelAPI.getInstance(context, context.getString(R.string.mixpanel_token));
    }

    @Provides @Singleton MixPanel provideMixPanel(MixpanelAPI mixpanelAPI) {
        return new MixPanel(mixpanelAPI);
    }

    @Provides MixpanelAPI.People providePeople(MixpanelAPI mixpanelAPI) {
        return mixpanelAPI.getPeople();
    }
}
