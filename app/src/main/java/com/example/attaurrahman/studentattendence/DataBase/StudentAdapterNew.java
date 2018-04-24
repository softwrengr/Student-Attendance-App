package com.example.attaurrahman.studentattendence.DataBase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attaurrahman.studentattendence.R;

import java.util.List;

/**
 * Created by AttaUrRahman on 3/12/2018.
 */

public class StudentAdapterNew extends RecyclerView.Adapter<StudentAdapterNew.ViewHolder> {

    public StudentAdapterNew(List<ListHelper> listHelpers, Context context) {
        this.listHelpers = listHelpers;
        this.context = context;
    }

    private List<ListHelper> listHelpers;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_attendance_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListHelper listHelper = listHelpers.get(position);

   holder.tvStudentName.setText(listHelper.getStudent_name());
   holder.tvStudentClass.setText(listHelper.getStudent_class());
   holder.tvRollNumber.setText(listHelper.getStudent_roll_number());
   holder.tvFacterCNIC.setText(listHelper.getFather_cnic());

    }

    @Override
    public int getItemCount() {
        return listHelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvStudentName;
        TextView tvStudentClass;
        TextView tvRollNumber ;
        TextView tvFacterCNIC ;
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);


            tvStudentName = itemView.findViewById(R.id.tvStudnetName);
            tvStudentClass = itemView.findViewById(R.id.tvStudentClass);
            tvRollNumber = itemView.findViewById(R.id.tvStudentRollNumber);
            tvFacterCNIC = itemView.findViewById(R.id.tvFatherCNIC);
            imageView= itemView.findViewById(R.id.iv_image);

        }
    }
}
