package com.flatstack.android.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.flatstack.android.ApiUtils;
import com.flatstack.android.utils.Rxs;
import com.flatstack.android.weather.models.Unit;
import com.flatstack.android.weather.models.Weather;

import rx.Subscription;

/**
 * Created by ereminilya on 11/4/17.
 */

public class WeatherPresenter {

    private static final String ICON_URL = "http://openweathermap.org/img/w/%s.png";

    @Nullable private WeatherView  view;
    @Nullable private Subscription retrievingWeatherSubs;

    @Nullable private Weather weather;

    public void attach(WeatherView view) {
        this.view = view;
        if (weather == null) {
            retrieveWeather();
        } else {
            showWeather(weather);
        }
    }

    private void retrieveWeather() {
        if (retrievingWeatherSubs != null) return;
        if (view != null) {
            view.showProgress();
        }
        retrievingWeatherSubs = ApiUtils.getInstance().weather(55.8017733, 49.1842741)
                .compose(Rxs.doInBackgroundDeliverToUI())
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.hideProgress();
                    }
                    retrievingWeatherSubs = null;
                })
                .subscribe(this::showWeather, error -> {
                    if (view != null) {
                        view.errorRetrievingWeather(error);
                    }
                });
    }

    private void showWeather(@NonNull Weather weather) {
        this.weather = weather;
        if (view != null) {
            view.showGeneralState(weather.getGeneralState());
            view.showHumidity(weather.getHumidity());
            view.showTemperature(weather.getTemperature(Unit.CELSILUS));
            view.showWeatherStateImage(String.format(ICON_URL, weather.getIconName()));
        }
    }

    public void onRefreshButtonClicked() {
        if (retrievingWeatherSubs != null) {
            if (view != null) {
                view.notifyWhatRefreshingInProgress();
            }
        } else {
            retrieveWeather();
        }
    }

    public void detach() {
        this.view = null;
    }
}