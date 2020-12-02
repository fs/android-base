package com.flatstack.android.di.modules

import com.apollographql.apollo.ApolloClient
import com.flatstack.android.BuildConfig
import com.flatstack.android.model.network.AwsImageUploader
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val netModule = Kodein.Module(name = "netModule") {
    bind<Interceptor>() with singleton { AuthorizationInterceptor(instance()) }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
            //.addInterceptor(AuthorizationInterceptor(instance()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    bind<ApolloClient>() with singleton {
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(instance())
            .build()
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(BuildConfig.RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(instance())
            .build()
    }

    bind<AwsImageUploader>() with singleton { createImageUploader(instance()) }


}

fun createImageUploader(retrofit: Retrofit): AwsImageUploader = retrofit.create(AwsImageUploader::class.java)
