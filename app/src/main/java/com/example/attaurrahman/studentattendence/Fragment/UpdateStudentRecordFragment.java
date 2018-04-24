package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateStudentRecordFragment extends Fragment implements View.OnClickListener{
    View parentView;
    Crud crud;

    @BindView(R.id.etUpdateName)EditText etUpdateName;
    @BindView(R.id.etUpdateRollNumber)EditText etUpdateRollNumber;
    @BindView(R.id.etUpdateClass)EditText etUpdateClass;
    @BindView(R.id.etUpdateFatherCNIC)EditText etUpdateFatherCNIC;

    @BindView(R.id.btnUpdateName)Button btnUpdateName;
    @BindView(R.id.btnUpdateRollNumber)Button btnUpdateRollNumber;
    @BindView(R.id.btnUpdateClass)Button btnUpdateClass;
    @BindView(R.id.btnUpdateFatherCNIC)Button btnUpdateFatherCNIC;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_update_student_record, container, false);

        ButterKnife.bind(this.parentView);
        crud = new Crud(getActivity());






        return parentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnUpdateName:

                break;
            case R.id.btnUpdateRollNumber:


                break;
            case R.id.btnUpdateClass:

                break;
            case R.id.btnUpdateFatherCNIC:


                break;



        }
    }
}
