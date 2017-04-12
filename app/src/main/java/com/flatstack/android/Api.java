package com.flatstack.android;

import com.flatstack.android.weather.models.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ereminilya on 26/3/17.
 */

public interface Api {

    String BASE_URL = BuildConfig.API_URL;
    String API_KEY  = BuildConfig.API_KEY;

    @GET("data/2.5/weather")
    Observable<Weather> weather(@Query("lat") double lat, @Query("lon") double lon);
}