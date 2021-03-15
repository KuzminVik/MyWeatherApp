package ru.geekbrains.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {
    // Имя настроек
    private static final String NameSharedPreference = "WEATHER_THEME";

    // Имя параметра в настройках
    private static final String IsDarkTheme = "IS_DARK_THEME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.ThemeMyApp);
        }


    }

    // Чтение настроек, параметр тема
    protected boolean isDarkTheme() {
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию true
        return sharedPref.getBoolean(IsDarkTheme, true);
    }

    // Сохранение настроек
    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }

}