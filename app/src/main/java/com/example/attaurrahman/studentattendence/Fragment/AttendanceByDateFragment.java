package com.example.attaurrahman.studentattendence.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.DataBase.ListHelper;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;


public class AttendanceByDateFragment extends Fragment {



    View parentView;
    Crud crud;
    String str_student_name, str_student_roll_number, str_date_format, str_student_father_cnic, str_student_attendce,str_date,str_2date;
    List<ListHelper> student_listHelpers;


    ArrayAdapter arrayAdapter;
    @BindView(R.id.lv_attendancelist)
    ListView lv_attendancelist;
    @BindView(R.id.btnSumbmitDate)Button btnSumbmitDate;
    CustomAdapter adapter;
    Parcelable state;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_attendance_by_date, container, false);

        ButterKnife.bind(this, parentView);

        crud =  new Crud(getActivity());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
        str_date = mdformat.format(calendar.getTime());
        crud.selectData(str_date);

        crud = new Crud(getActivity());
        student_listHelpers = crud.attendancelist(str_date);
        adapter = new CustomAdapter(getActivity(),student_listHelpers);
        adapter.notifyDataSetChanged();
        lv_attendancelist.setAdapter(adapter);

        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd ");
        str_2date = dformat.format(calendar.getTime());
        btnSumbmitDate.setText(str_2date);







        return parentView;
    }

    private class CustomAdapter extends ArrayAdapter {
        Context context;
        List<ListHelper> listHelpers;
        TextView tvAttendance;

        public CustomAdapter(@NonNull Context context, List list) {
            super(context, R.layout.student_attendance_list, list);

            this.context = context;
            this.listHelpers = list;


        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.student_attendance_list, parent, false);

            final ListHelper listHelper = student_listHelpers.get(position);
            TextView tvStudentName = view.findViewById(R.id.tvStudnetName);
            TextView tvStudentClass = view.findViewById(R.id.tvStudentClass);
            TextView tvRollNumber = view.findViewById(R.id.tvStudentRollNumber);
            TextView tvFacterCNIC = view.findViewById(R.id.tvFatherCNIC);
            tvAttendance = view.findViewById(R.id.tvAttendance);
            TextView tvDate  =view.findViewById(R.id.tvDate);
            final Button btnAbsent = view.findViewById(R.id.btnAbsent);
            Button btnPresent = view.findViewById(R.id.btnPresent);

            final LinearLayout ll_student_attendance_list = view.findViewById(R.id.ll_student_attendance_list);

            tvStudentName.setText(listHelper.getStudent_name().toString());
            tvStudentClass.setText(listHelper.getStudent_class().toString());
            tvRollNumber.setText(String.valueOf(listHelper.getStudent_roll_number()));
            tvFacterCNIC.setText(String.valueOf(listHelper.getFather_cnic()));
            tvAttendance.setText(String.valueOf(listHelper.getStudent_attendec()));
            tvDate.setText(String.valueOf(listHelper.getDate()));
            


            final String str_rollNumber = String.valueOf(listHelper.getStudent_roll_number());


            ll_student_attendance_list.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    registerForContextMenu(ll_student_attendance_list);
                    str_student_roll_number = String.valueOf(listHelper.getStudent_roll_number());
                    return false;
                }
            });


            btnPresent.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    state = lv_attendancelist.onSaveInstanceState();

                    str_student_attendce = "Present";
                    crud.UpdateAttendance(str_rollNumber, str_student_attendce);
                    student_listHelpers = crud.attendancelist(str_date);

                    lv_attendancelist.onRestoreInstanceState(state);
                    Toast.makeText(getActivity(), "Present", LENGTH_SHORT).show();

                }
            });
            btnAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    state = lv_attendancelist.onSaveInstanceState();

                    str_student_attendce = "Absent";
                    crud.UpdateAttendance(str_rollNumber, str_student_attendce);
                    student_listHelpers = crud.attendancelist(str_date);

                    lv_attendancelist.onRestoreInstanceState(state);
                    Toast.makeText(getActivity(), "Absent", LENGTH_SHORT).show();


                }
            });



            return view;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.edit_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_edit:
                Toast.makeText(getActivity(), "Edit", LENGTH_SHORT).show();
                break;
            case R.id.delete:
                crud.Delete(str_student_roll_number);
                Utilities.connectFragmentWithOutBackStack(getActivity(), new AttendanceFragment());
        }
        return true;
    }
}