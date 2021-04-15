package ru.geekbrains.myapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import ru.geekbrains.myapp.model.ConnectController;


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private static String currentCity;
    private OnOpenListener myListener;
    private TextInputEditText searchCity;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                if(currentCity.equals("") || currentCity.equals(" ")){
                    Toast.makeText(getContext(), R.string.error_name_city_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkText(currentCity)){
                    Toast.makeText(getContext(), R.string.error_name_city, Toast.LENGTH_SHORT).show();
                    return;
                }
                ConnectController connectController = new ConnectController(currentCity);
                Thread tr = new Thread(connectController);
                tr.start();
                try {
                    tr.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<Parcelable> parcelables = connectController.getParcelables();

                if(parcelables != null){
                    myListener.onOpenWeatherFragment(parcelables);
                }else{
                    if(Keys.LOG){
                        Log.d(TAG, "SearchFragment: parcelables is null");
                    }
                }
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
        if (context instanceof OnOpenListener) {
            myListener = (OnOpenListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }

    boolean checkText(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==' ' && count==0){
                count++;
            }else if(s.charAt(i)!=' ' && count==1){
                count = 0;
            }
            else if(s.charAt(i)==' ' && count!=0){
                return false;
            }
        }
        return true;
    }
}