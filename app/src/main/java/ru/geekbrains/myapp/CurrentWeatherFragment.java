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
        // Required empty public constructor
    }

    public static CurrentWeatherFragment create (ParcelWeather parcel) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(Keys.PARCEL, parcel);
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
            ParcelWeather parcelWeather = getArguments().getParcelable(Keys.PARCEL);
            if(parcelWeather != null){
                twCity.setText(parcelWeather.getCity());
                String stringTemp = getString(R.string.temperature, parcelWeather.getTemp());
                twTemp.setText(stringTemp);
                String stringPressure = getString(R.string.pressure, parcelWeather.getPressure());
                twPressure.setText(stringPressure);
                String stringHumidity = getString(R.string.humidity, parcelWeather.getHumidity());
                twHumidity.setText(stringHumidity);
            }else{
                if(Keys.LOG){
                    Log.d(TAG, "parcelWeather is null");
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