package ru.geekbrains.myapp;

import android.os.Parcelable;

import java.util.ArrayList;

public interface OnOpenListener {
    void onOpenWeatherFragment(ArrayList<Parcelable> parcelables);
    void onOpenAboutProgramFragment();
    void onOpenAboutDevFragment();
}
