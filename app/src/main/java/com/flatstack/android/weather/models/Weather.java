package com.flatstack.android.weather.models;

/**
 * Created by ereminilya on 26/3/17.
 */
public class Weather {

    private String         name;
    private WeatherState[] weather;
    private Main           main;
    private Wind           wind;

    public String getIconName() {
        return weather[0].icon;
    }

    public String getGeneralState() {
        return weather[0].description;
    }

    public int getHumidity() {
        return main.humidity;
    }

    public int getTemperature(Unit unit) {
        if (unit == Unit.CELSILUS) {
            return (int) (main.temp - 273.15);
        }
        throw new IllegalStateException("does not implement suka");
    }

    public static class WeatherState {
        private String main;
        private String description;
        private String icon;
    }

    public static class Main {
        private double temp;
        private int    pressure;
        private int    humidity;
    }

    public static class Wind {
        private int speed;
        private int deg;
    }
}