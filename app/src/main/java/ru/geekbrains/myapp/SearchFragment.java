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


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private OnOpenWeatherDataListener myListener;
    TextInputEditText searchCity;
    ParcelWeather parcelWeather = new ParcelWeather("Калуга", 18, 85, 800);

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
                if(Keys.LOG){
                    Log.d(TAG, "onClick: мы нажали на кнопку");
                }
                myListener.onOpenWeatherFragment(parcelWeather);
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