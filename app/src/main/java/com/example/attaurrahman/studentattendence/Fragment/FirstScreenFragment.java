package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.attaurrahman.studentattendence.R;


public class FirstScreenFragment extends Fragment {

    View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_first_screen, container, false);


        return parentView;
    }

}
