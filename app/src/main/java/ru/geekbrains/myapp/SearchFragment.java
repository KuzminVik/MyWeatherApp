package ru.geekbrains.myapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.myapp.model.WeatherRequest;


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static String currentCity;
    private static final String WEATHER_API_KEY = ",ru&units=metric&appid=71fdca2d824eb807d7e97e81403a67e3";
    private static WeatherRequest weatherRequest;
    private OnOpenWeatherDataListener myListener;
    private TextInputEditText searchCity;

//    ParcelWeather parcelWeather = new ParcelWeather("Калуга", 18, 85, 800);

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Keys.LOG){
            Log.d(TAG, "onCreate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchCity = view.findViewById(R.id.ti_editText);
        Button btn =  view.findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCity = searchCity.getText().toString();
                if(currentCity==null){
                    Toast.makeText(getContext(), "currentCity is null", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    final URL uri = new URL(WEATHER_URL + currentCity + WEATHER_API_KEY);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        weatherRequest.setName(currentCity);
                        } catch (IOException e) {
                            e.printStackTrace();
                            if(Keys.LOG){
                                Log.d(TAG, "Fail connection");
                            }
                        }finally {
                            if (urlConnection != null) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                myListener.onOpenWeatherFragment(weatherRequest);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_Layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] data = getArguments().getStringArray(Keys.KEY);
        SearchCityAdapter searchCityAdapter = new SearchCityAdapter(data);
        recyclerView.setAdapter(searchCityAdapter);
        // Установим слушателя
        searchCityAdapter.SetOnItemClickListener(new SearchCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), String.format("%s - %d", ((TextView)view).getText(), position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenWeatherDataListener) {
            myListener = (OnOpenWeatherDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }
}