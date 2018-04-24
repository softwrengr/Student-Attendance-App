package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.DataBase.ListHelper;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;
import com.phlox.datepick.CalendarNumbersView;
import com.phlox.datepick.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckAttendanceFragment extends Fragment {

    View parentView;
    Crud crud;
    List<ListHelper> student_attendance_listHelpers;



    @BindView(R.id.lvCheckAttendance)ListView lvCheckAttendance;
    @BindView(R.id.btnDate)Button btnDate;
    @BindView(R.id.btnSetDate)Button btnSetDate;

    private PopupWindow calendarPopup;
    String str_date,str_button_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_check_attendance, container, false);

        ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());





        btnSetDate.setOnClickListener(onButtonClickListener);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String  date = btnSetDate.getText().toString();
                student_attendance_listHelpers = crud.checkAttendance(date);
                CustomAdapter adapter = new CustomAdapter(getActivity(),student_attendance_listHelpers);
                lvCheckAttendance.setAdapter(adapter);
                registerForContextMenu(lvCheckAttendance);



            }
        });



        return parentView;
    }

    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (calendarPopup == null) {
                calendarPopup = new PopupWindow(getActivity());
                CalendarPickerView calendarView = new CalendarPickerView(getActivity());
                calendarView.setListener(dateSelectionListener);
                calendarPopup.setContentView(calendarView);
                calendarPopup.setWindowLayoutMode(
                        View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                calendarPopup.setHeight(1);
                calendarPopup.setWidth(view.getWidth());
                calendarPopup.setOutsideTouchable(true);
            }
            calendarPopup.showAsDropDown(view);
        }
    };

    private CalendarNumbersView.DateSelectionListener dateSelectionListener = new CalendarNumbersView.DateSelectionListener() {
        @Override
        public void onDateSelected(Calendar selectedDate) {
            if (calendarPopup.isShowing()) {
                calendarPopup.getContentView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calendarPopup.dismiss();
                    }
                }, 500);//For clarity, we close the popup not immediately.
            }


            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            str_date = (formatter.format(selectedDate.getTime()));

            SimpleDateFormat formatterButtonDate = new SimpleDateFormat("yyyy-MM-dd");
            str_button_date = (formatterButtonDate.format(selectedDate.getTime()));
            btnSetDate.setText(str_button_date);

        }
    };
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

            final ListHelper listHelper = student_attendance_listHelpers.get(position);
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
