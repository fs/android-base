package com.flatstack.android.weather;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flatstack.android.R;
import com.flatstack.android.utils.ui.BaseActivity;
import com.flatstack.android.utils.ui.UiInfo;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ereminilya on 26/3/17.
 */

public class WeatherScreen extends BaseActivity implements WeatherView {

    @Bind(R.id.general)  TextView  uiGeneral;
    @Bind(R.id.humidity) TextView  uiHumidity;
    @Bind(R.id.icon)     ImageView uiIcon;
    @Bind(R.id.temp)     TextView  uiTemp;
    @Bind(R.id.progress) View      uiProgress;

    @NonNull @Override public UiInfo getUiInfo() {
        return new UiInfo(R.layout.screen_weather);
    }

    private WeatherPresenter weatherPresenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherPresenter = new WeatherPresenter();
    }

    @Override protected void onResume() {
        super.onResume();
        weatherPresenter.attach(this);
    }

    @Override public void showProgress() {
        uiProgress.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        uiProgress.setVisibility(View.GONE);
    }

    @Override public void showGeneralState(String state) {
        uiGeneral.setText(state);
    }

    @Override public void showTemperature(int temperatureInCelsilus) {
        uiTemp.setText(getString(R.string.temperature_celsius, temperatureInCelsilus));
    }

    @Override public void showHumidity(@IntRange(from = 0, to = 100) int percent) {
        uiHumidity.setText(getString(R.string.humidity, percent));
    }

    @Override public void showWeatherStateImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(uiIcon);
    }

    @Override public void errorRetrievingWeather(Throwable error) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override public void notifyWhatRefreshingInProgress() {
        Toast.makeText(this, R.string.refresh_in_progess, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.refresh) void onRefreshClick() {
        weatherPresenter.onRefreshButtonClicked();
    }

    @Override protected void onPause() {
        weatherPresenter.detach();
        super.onPause();
    }
}