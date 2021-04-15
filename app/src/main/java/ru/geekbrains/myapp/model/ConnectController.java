package ru.geekbrains.myapp.model;

import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.myapp.Keys;

public class ConnectController extends Thread{
    private static final String TAG = "ConnectController";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String WEATHER_API_KEY = ",ru&units=metric&appid=71fdca2d824eb807d7e97e81403a67e3&lang=ru";
    private static final String URL_PART1 ="https://api.openweathermap.org/data/2.5/onecall?lat=";
    private static final String URL_PART2 ="&lon=";
    private static final String URL_PART3 ="&exclude=current,minutely,hourly,alerts&units=metric&appid=71fdca2d824eb807d7e97e81403a67e3&lang=ru";
    private final String currentCity;
    private WeatherRequest weatherRequest;
    private WeekRequest weekRequest;
    private ArrayList<Parcelable> parcelables;

    volatile boolean weatherIsReady = false;
    volatile boolean weekIsReady = false;

    private String lat;
    private String lon;

    public ArrayList<Parcelable> getParcelables() {
        return parcelables;
    }

    public ConnectController(String currentCity) {
        this.currentCity = currentCity;
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public WeekRequest getWeekRequest() {
        return weekRequest;
    }

    @Override
    public void run() {
        try {
            URL uri1 = new URL(WEATHER_URL + currentCity + WEATHER_API_KEY);
            HttpsURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpsURLConnection) uri1.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String result = getLines(in);
                    Gson gson = new Gson();
                    weatherRequest = gson.fromJson(result, WeatherRequest.class);

                    if(weatherRequest==null){
                        if(Keys.LOG){
                            Log.d(TAG, "weatherRequest==null!!!!!!!!!!!!!!!!!!");
                        }
                        return;
                    }

                    lat = String.valueOf(weatherRequest.getCoord().getLat());
                    lon = String.valueOf(weatherRequest.getCoord().getLon());

                    if(Keys.LOG){
                        Log.d(TAG, "URL_PART1 + lat + URL_PART2 + lon + URL_PART3 = "+URL_PART1 + lat + URL_PART2 + lon + URL_PART3);
                    }

                    weatherIsReady = true;
                    } catch (IOException e) {
                        e.printStackTrace();
    //                        R.string.error_city_not_found
                        if(Keys.LOG){
                            Log.d(TAG, "Fail connection");
                        }
                    }finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        while(!weatherIsReady){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            URL uri2 = new URL(URL_PART1 + lat + URL_PART2 + lon + URL_PART3);
            HttpsURLConnection urlConnection2 = null;
            try {
                urlConnection2 = (HttpsURLConnection) uri2.openConnection();
                urlConnection2.setRequestMethod("GET");
                urlConnection2.setReadTimeout(10000);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));
                String result2 = getLines(in);
                Gson gson2 = new Gson();
                weekRequest = gson2.fromJson(result2, WeekRequest.class);
                weekIsReady = true;
                } catch (IOException e) {
                     e.printStackTrace();
    //               R.string.error_city_not_found
                     if(Keys.LOG){
                         Log.d(TAG, "Fail connection");
                     }
                }finally {
                     if (urlConnection2 != null) {
                        urlConnection2.disconnect();
                     }
                }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        while(!weekIsReady){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(Keys.LOG){
            Log.d(TAG, "weekRequest.getTimezone()+weekRequest.getDaily()[0].getDt() "+String.valueOf(weekRequest.getTimezone()+" "+weekRequest.getDaily()[0].getDt()));
        }

        parcelables = new ArrayList<>();
        parcelables.add(0, weatherRequest);
        parcelables.add(1, weekRequest);
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

}
