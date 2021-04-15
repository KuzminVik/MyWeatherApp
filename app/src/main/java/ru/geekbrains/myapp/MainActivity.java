package ru.geekbrains.myapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;

public class MainActivity extends BaseActivity implements OnOpenListener{
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                return true;
            case R.id.action_info:
                onOpenAboutProgramFragment();
                return true;
            case R.id.action_developer:
                onOpenAboutDevFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void onOpenWeatherFragment(ArrayList<Parcelable> parcelables) {
        if(parcelables != null){
            CurrentWeatherFragment fr = CurrentWeatherFragment.create(parcelables);
            FragmentManager fragmentManager = getSupportFragmentManager();
//            String[] data = getResources().getStringArray(R.array.months);
//            Bundle bundle = new Bundle();
//            bundle.putStringArray(Keys.KEY, data);
//            fr.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.searchFragment, fr)
                    .addToBackStack("name")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else{
            if(Keys.LOG){
                Log.d(TAG, "MainActivity onOpenWeatherFragment: weatherRequest and weekRequest is null");
            }
        }
    }

    @Override
    public void onOpenAboutProgramFragment(){
        AboutProgramFragment fragment = AboutProgramFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.searchFragment,fragment).addToBackStack("name2").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    @Override
    public void onOpenAboutDevFragment(){
        AboutDevFragment fragment = AboutDevFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.searchFragment,fragment).addToBackStack("name3").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}