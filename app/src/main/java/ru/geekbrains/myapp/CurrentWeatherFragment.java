package ru.geekbrains.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;


import ru.geekbrains.myapp.model.WeatherRequest;
import ru.geekbrains.myapp.model.WeekRequest;

public class CurrentWeatherFragment extends Fragment {
    private static final String TAG = "CurrentWeatherFragment";
    private static final int SETTING_CODE = 88;
    private WeatherRequest weatherRequest;
    private WeekRequest weekRequest;

    private TextView tvCity;
    private TextView tvTemp;
    private TextView tvPressure;
    private TextView tvHumidity;
    private Button b1;
    private Button b2;

    public CurrentWeatherFragment() {
    }

    public static CurrentWeatherFragment create (ArrayList<Parcelable> parcelables) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Keys.PARCEL, parcelables);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        b1 = view.findViewById(R.id.button);
        b2 = view.findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, SETTING_CODE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivityForResult(intent, SETTING_CODE);
            }
        });
        tvCity = view.findViewById(R.id.city);
        tvTemp = view.findViewById(R.id.temp);
        tvPressure = view.findViewById(R.id.textView_pressur);
        tvHumidity = view.findViewById(R.id.textView_humidity);

        if(getArguments() != null){
            ArrayList<Parcelable> arr = getArguments().getParcelableArrayList(Keys.PARCEL);
            if(arr!=null){
                weatherRequest = (WeatherRequest) arr.get(0);
                weekRequest = (WeekRequest) arr.get(1);
            }

            if(weatherRequest != null){
                tvCity.setText(weatherRequest.getName());
                tvTemp.setText(getString(R.string.temperature, weatherRequest.getMain().getTemp()));
                tvPressure.setText(getString(R.string.pressure, weatherRequest.getMain().getPressure()));
                tvHumidity.setText(getString(R.string.humidity, weatherRequest.getMain().getHumidity()));
                            }else{
                if(Keys.LOG){
                    Log.d(TAG, "weatherRequest and weekRequest is null");
                }
            }
        }else{
            if(Keys.LOG){
                Log.d(TAG, "getArguments() is null");
            }
        }

        if(weekRequest.getDaily()!=null){
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_week);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(lm);
            String[] data = getResources().getStringArray(R.array.months);
            WeekWeatherAdapter adapter = new WeekWeatherAdapter(weekRequest, data);
            recyclerView.setAdapter(adapter);
        }else{
            if(Keys.LOG){
                Log.d(TAG, "weatherRequest is null");
            }
        }
        return view;
    }
}