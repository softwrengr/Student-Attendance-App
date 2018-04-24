package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.attaurrahman.studentattendence.Activities.MainActivity;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainLoginFragment extends Fragment {
    View parentView;
    @BindView(R.id.btnParent)Button btnParent;
    @BindView(R.id.btnTeacher)Button btnTeacher;
    @BindView(R.id.btnAttendance)Button btnAttendance;
    @BindView(R.id.btnCheckAttendance)Button btnCheckAttendance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main_login, container, false);

        ButterKnife.bind(this,parentView);

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.connectFragment(getActivity(),new ParentFragment());

            }
        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.connectFragment(getActivity(),new AttendanceFragment());
            }
        });

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.connectFragment(getActivity(),new AttendanceByDateFragment());
            }
        });
        btnCheckAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.connectFragment(getActivity(),new CheckAttendanceFragment());
            }
        });
        return parentView;
    }

}
