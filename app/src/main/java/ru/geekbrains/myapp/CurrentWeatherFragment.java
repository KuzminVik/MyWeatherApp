package ru.geekbrains.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.geekbrains.myapp.model.WeatherRequest;

public class CurrentWeatherFragment extends Fragment {
    private static final String TAG = "CurrentWeatherFragment";
    private static final int SETTING_CODE = 88;

    private TextView twCity;
    private TextView twTemp;
    private TextView twPressure;
    private TextView twHumidity;
    private Button b1;
    private Button b2;


    public CurrentWeatherFragment() {

    }

    public static CurrentWeatherFragment create (WeatherRequest weatherRequest) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(Keys.PARCEL, weatherRequest);
        fragment.setArguments(args);
        return fragment;
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
                /////
            }
        });
        twCity = view.findViewById(R.id.city);
        twTemp = view.findViewById(R.id.temp);
        twPressure = view.findViewById(R.id.textView_pressur);
        twHumidity = view.findViewById(R.id.textView_humidity);
        if(getArguments() != null){

            WeatherRequest weatherRequest = getArguments().getParcelable(Keys.PARCEL);
            if(weatherRequest != null){
                twCity.setText(weatherRequest.getName());
                twTemp.setText(getString(R.string.temperature, weatherRequest.getMain().getTemp()));
                twPressure.setText(getString(R.string.pressure, weatherRequest.getMain().getPressure()));
                twHumidity.setText(getString(R.string.humidity, weatherRequest.getMain().getHumidity()));
            }else{
                if(Keys.LOG){
                    Log.d(TAG, "weatherRequest is null");
                }
            }
        }else{
            if(Keys.LOG){
                Log.d(TAG, "getArguments() is null");
            }
        }
        if(Keys.LOG){
            Log.d(TAG, "onCreateView");
        }
        return view;
    }
}