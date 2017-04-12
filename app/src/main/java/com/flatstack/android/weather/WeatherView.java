package com.flatstack.android.weather;

import android.support.annotation.IntRange;

/**
 * Created by ereminilya on 11/4/17.
 */

public interface WeatherView {

    void showProgress();

    void hideProgress();

    void showGeneralState(String state);

    void showTemperature(int temperatureInCelsilus);

    void showHumidity(@IntRange(from = 0, to = 100) int percent);

    void showWeatherStateImage(String imageUrl);

    void errorRetrievingWeather(Throwable error);

    void notifyWhatRefreshingInProgress();
}