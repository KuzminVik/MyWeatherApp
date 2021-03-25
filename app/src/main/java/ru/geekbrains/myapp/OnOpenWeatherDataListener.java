package ru.geekbrains.myapp;

import ru.geekbrains.myapp.model.WeatherRequest;

public interface OnOpenWeatherDataListener {
    void onOpenWeatherFragment(WeatherRequest weatherRequest);
}
