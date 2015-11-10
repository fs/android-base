package com.flatstack.android.dagger.components;

import com.flatstack.android.App;
import com.flatstack.android.dagger.modules.AppModule;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by IlyaEremin on 13/04/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
    App application();
    OkHttpClient okHttpClient();
    Picasso picasso();

}
