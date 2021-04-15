package ru.geekbrains.myapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutDevFragment extends Fragment {
    private static final String TAG = "AboutDevFragment";
    private TextView tvContent;

    public AboutDevFragment() {

    }

    public static AboutDevFragment newInstance() {
        return new AboutDevFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_dev, container, false);
        tvContent = view.findViewById(R.id.tv_about_dev);
        tvContent.setText(R.string.text_content_developer);
        return view;
    }
}