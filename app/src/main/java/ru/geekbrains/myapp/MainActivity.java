package ru.geekbrains.myapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;

import ru.geekbrains.myapp.model.WeatherRequest;

public class MainActivity extends BaseActivity implements OnOpenWeatherDataListener{
    private static final int SETTING_CODE = 88;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SearchFragment searchFragment = new SearchFragment();
        if(savedInstanceState==null){
            String[] data = getResources().getStringArray(R.array.city);
            Bundle bundle = new Bundle();
            bundle.putStringArray(Keys.KEY, data);
            searchFragment.setArguments(bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.searchFragment, searchFragment, Keys.FRAGMENT_SEARCH);
            ft.commit();
        }

        if(Keys.LOG){
            Log.d(TAG, "onCreate");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE){
            recreate();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(Keys.LOG){
            Log.d(TAG, "onStart");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        if(Keys.LOG){
            Log.d(TAG, "onRestoreInstanceState");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Keys.LOG){
            Log.d(TAG, "onResume");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Keys.LOG){
            Log.d(TAG, "onPause");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        if(Keys.LOG){
            Log.d(TAG, "onSaveInstanceState");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Keys.LOG){
            Log.d(TAG, "onStop");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(Keys.LOG){
            Log.d(TAG, "onRestart");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Keys.LOG){
            Log.d(TAG, "onDestroy");
        }
    }

    @Override
    public void onOpenWeatherFragment(WeatherRequest weatherRequest) {
        if(weatherRequest != null){
            CurrentWeatherFragment fr = CurrentWeatherFragment.create(weatherRequest);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.searchFragment, fr)
                    .addToBackStack("name")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else{
            if(Keys.LOG){
                Log.d(TAG, "onOpenWeatherFragment: weatherRequest is null");
            }
        }

    }
}