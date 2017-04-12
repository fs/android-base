package com.flatstack.android.weather.models;

/**
 * Created by ereminilya on 26/3/17.
 */
public class Weather {

    private String     name;
    private _Weather[] weather;
    private _Main      main;
    private Wind       wind;

    public String getIconName() {
        return weather[0].icon;
    }

    public int getTemperature(Unit unit) {
        if (unit == Unit.CELSILUS) {
            return (int) (main.temp - 273.15);
        }
        throw new IllegalStateException("does not implement suka");
    }

    public static class _Weather {
        private String main;
        private String description;
        private String icon;
    }

    public static class _Main {
        private double temp;
        private double temp_min;
        private double temp_max;
        private int    pressure;
        private int    humidity;
    }

    public static class Wind {
        private int speed;
        private int deg;
    }

    public String getGeneralState() {
        return weather[0].description;
    }

    public int getHumidity() {
        return main.humidity;
    }

}