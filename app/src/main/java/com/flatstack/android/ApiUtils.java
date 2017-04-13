package com.flatstack.android;

import android.support.annotation.NonNull;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ereminilya on 26/3/17.
 */

public class ApiUtils {

    private static volatile Api sApiInstance;

    public static Api getInstance() {
        Api localInstance = sApiInstance;
        if (localInstance == null) {
            synchronized (Api.class) {
                localInstance = sApiInstance;
                if (localInstance == null) {
                    localInstance = new Retrofit.Builder()
                            .client(getHttpClient())
                            .baseUrl(Api.BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(Api.class);
                    sApiInstance = localInstance;
                }
            }
        }
        return localInstance;
    }

    @NonNull private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.addInterceptor(chain -> {
//                        Request request = chain.request().newBuilder()
//                                .addHeader("Authorization", "Your Token ept")
//                                .build();
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("APPID", Api.API_KEY)
                    .build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);
        });
        return httpClientBuilder.build();
    }
}