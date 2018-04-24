package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.DataBase.ListHelper;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;


public class ParentFragment extends Fragment {
    View parentView;
    Crud crud;
    List<ListHelper> student_fatherCNIC_listHelpers;



    @BindView(R.id.lvFatherCnic)ListView lvFatherCnic;
    @BindView(R.id.btnCnicSubmit)Button btnCnicSubmit;
    @BindView(R.id.etFatherCnic)EditText etFatherCnic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       parentView = inflater.inflate(R.layout.fragment_parent, container, false);

       ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());


        btnCnicSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               btnCnicSubmit.setVisibility(View.GONE);

               String  cnic = Utilities.editTextDeclaration(R.id.etFatherCnic, parentView).getText().toString();
               student_fatherCNIC_listHelpers = crud.father_cnic_data(cnic);
               CustomAdapter adapter = new CustomAdapter(getActivity(),student_fatherCNIC_listHelpers);
               lvFatherCnic.setAdapter(adapter);
               registerForContextMenu(lvFatherCnic);


               
           }
       });
        etFatherCnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCnicSubmit.setVisibility(View.VISIBLE);

            }
        });




        return parentView;
    }

    private class CustomAdapter extends ArrayAdapter {
        Context context;
        List<ListHelper> listHelpers;

        public CustomAdapter( Context context, List list) {
            super(context, R.layout.student_father_cnic_data_list, list);

            this.context = context;
            this.listHelpers = list;
        }

        @NonNull
        @Override
        public View getView(final int position,  View convertView,  ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.student_father_cnic_data_list, parent, false);

            final ListHelper listHelper = student_fatherCNIC_listHelpers.get(position);
            TextView tvStudentNameFcnic = view.findViewById(R.id.tvStudentNameFcnic);
            TextView tvStudentClassFcnic = view.findViewById(R.id.tvStudentClassFcnic);
            TextView tvStudentRollNumberFcnic = view.findViewById(R.id.tvStudentRollNumberFcnic);
            TextView tvStudentAttendanceFcnic = view.findViewById(R.id.tvStudentAttendanceFcnic);
            LinearLayout ll_student_father_cnic_record = view.findViewById(R.id.ll_student_father_cnic_record);

            tvStudentNameFcnic.setText(listHelper.getStudent_name().toString());
            tvStudentClassFcnic.setText(listHelper.getStudent_class().toString());
            tvStudentRollNumberFcnic.setText(String.valueOf(listHelper.getStudent_roll_number()));
            tvStudentAttendanceFcnic.setText(String.valueOf(listHelper.getStudent_attendec()));

            ll_student_father_cnic_record.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(getActivity(),String.valueOf(listHelper.getStudent_roll_number()), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            ll_student_father_cnic_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

            return view;

        }

    }



}
