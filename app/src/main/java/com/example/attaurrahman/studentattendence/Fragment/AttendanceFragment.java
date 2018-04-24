package com.example.attaurrahman.studentattendence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.DataBase.ListHelper;
import com.example.attaurrahman.studentattendence.DataBase.StudentAdapter;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;

public class AttendanceFragment extends Fragment {

    View parentView;
    Crud crud;
    String str_student_name, str_student_roll_number, str_student_class, str_student_father_cnic, str_student_attendce, str_date;
    List<ListHelper> listHelpers;


    ArrayAdapter arrayAdapter;
    @BindView(R.id.lv_studentlist)
    ListView lv_studentlist;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    Parcelable state;
    @BindView(R.id.rv_student) RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, parentView);
        state = lv_studentlist.onSaveInstanceState();
        lv_studentlist.onRestoreInstanceState(state);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        crud = new Crud(getActivity());
        listHelpers = crud.StudentAllRecordList();
        StudentAdapter studentAdapter = new StudentAdapter(listHelpers,getActivity());
        recyclerView.setAdapter(studentAdapter);










//        CustomAdapter adapter = new CustomAdapter(getActivity(), listHelpers);
//        adapter.notifyDataSetChanged();
//        lv_studentlist.setAdapter(adapter);
//


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.connectFragment(getActivity(), new StudentDetail());

            }
        });


        return parentView;
    }


//    public class CustomAdapter extends ArrayAdapter {
//        Context context;
//        List<ListHelper> listHelpers;
//        TextView tvAttendance;
//
//        public CustomAdapter(@NonNull Context context, List list) {
//            super(context, R.layout.student_attendance_list, list);
//
//            this.context = context;
//            this.listHelpers = list;
//
//        }
//
//        @NonNull
//        @Override
//        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = inflater.inflate(R.layout.student_record_list, parent, false);
//
//            final ListHelper listHelper = listHelpers.get(position);
//            TextView tvStudentName = view.findViewById(R.id.tvStudnetName);
//            TextView tvStudentClass = view.findViewById(R.id.tvStudentClass);
//            TextView tvRollNumber = view.findViewById(R.id.tvStudentRollNumber);
//            TextView tvFacterCNIC = view.findViewById(R.id.tvFatherCNIC);
//            ImageView imageView = view.findViewById(R.id.iv_image);
//
//
//            final LinearLayout ll_student_attendance_list = view.findViewById(R.id.ll_student_record);
//
//            tvStudentName.setText(listHelper.getStudent_name().toString());
//            tvStudentClass.setText(listHelper.getStudent_class().toString());
//            tvRollNumber.setText(String.valueOf(listHelper.getStudent_roll_number()));
//            tvFacterCNIC.setText(String.valueOf(listHelper.getFather_cnic()));
//            imageView.setImageURI(Uri.parse(listHelper.getImge_uri().toString()));
//
//
//            ll_student_attendance_list.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    registerForContextMenu(ll_student_attendance_list);
//                    str_student_roll_number = String.valueOf(listHelper.getStudent_roll_number());
//                    return false;
//                }
//            });
//
//
//            return view;
//        }
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        getActivity().getMenuInflater().inflate(R.menu.edit_menu, menu);
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.menu_edit:
//                Toast.makeText(getActivity(), "Edit", LENGTH_SHORT).show();
//                break;
//            case R.id.delete:
//                crud.Delete(str_student_roll_number);
//                Utilities.connectFragmentWithOutBackStack(getActivity(), new AttendanceFragment());
//        }
//        return true;
//    }
}


