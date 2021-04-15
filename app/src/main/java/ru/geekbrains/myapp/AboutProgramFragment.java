package ru.geekbrains.myapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutProgramFragment extends Fragment {
    private static final String TAG = "AboutProgramFragment";
    private TextView tvContent;

    public AboutProgramFragment() {
    }

    public static AboutProgramFragment newInstance() {
        return new AboutProgramFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_programm, container, false);
        tvContent = view.findViewById(R.id.tv_about_program);
        tvContent.setText(R.string.text_content_info);
        return view;
    }
}