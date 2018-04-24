package com.example.attaurrahman.studentattendence.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AttaUrRahman on 2/14/2018.
 */

public class Crud {
    private static SQLiteDatabase database,database1;

    private Context context;


    public Crud(Context context) {
        DBStudent dbStudent = new DBStudent(context);
        database = dbStudent.getWritableDatabase();
        database1 = dbStudent.getWritableDatabase();

        this.context = context;




    }
    public void insertData(String student_name, String student_roll_number, String student_class, String student_father_cnic, String student_attendance, String str_date, String image) {
        Cursor cursor = this.database.rawQuery("SELECT * FROM STUDENT_TABLE WHERE STUDENT_ROLL_NUMBER = '" + student_roll_number + "' ", null);

        if (cursor.moveToFirst()){

            Toast.makeText(context, "Already Exist", Toast.LENGTH_SHORT).show();

        }else {
            ContentValues values = new ContentValues();
            values.put("STUDENT_NAME", student_name);
            values.put("STUDENT_ROLL_NUMBER", student_roll_number);
            values.put("STUDENT_CLASS", student_class);
            values.put("STUDENT_ATTENDANCE", student_attendance);
            values.put("STUDENT_FATHER_CNIC", student_father_cnic);
            values.put("STUDENT_DATE", str_date);
            values.put("IMAGE",image);

            database.insert("STUDENT_TABLE", null, values);
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();

        }
    }



    public List selectData(String date) {



        List<String> list = new ArrayList<>();

        String query = "SELECT  * FROM STUDENT_TABLE";
        Cursor cursor = database.rawQuery(query, null);


        Cursor cursor1 = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + date + "' ", null);

        if (cursor1.moveToFirst()){

            Toast.makeText(context, "Already Exist", Toast.LENGTH_SHORT).show();

        }else {

            if (cursor.moveToFirst()) {
                do {


                    list.add(cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    list.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    list.add(cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    list.add(cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    list.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                    list.add(cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));

                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));

                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));

                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));

                    String str_father_cnic = String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC")));


                    ContentValues values = new ContentValues();
                    values.put("STUDENT_NAME", name);
                    values.put("STUDENT_ROLL_NUMBER", roll_number);
                    values.put("STUDENT_CLASS", str_class);
                    values.put("STUDENT_FATHER_CNIC", str_father_cnic);
                    values.put("STUDENT_DATE", date);

                    database1.insert("ATTENDANCE_TABLE", null, values);

                }
                while (cursor.moveToNext());
            }}
            return list;


    }



    public List<ListHelper> showHelperData() {

        List<ListHelper> list = new ArrayList<>();

        String query = "SELECT  * FROM ATTENDANCE_TABLE";

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                ListHelper helpers = new ListHelper();
                String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                helpers.setStudent_name(name);
                String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                helpers.setStudent_class(str_class);
                String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                helpers.setStudent_attendec(str_attendance);
                String str_father_cnic = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                helpers.setFather_cnic(Integer.parseInt(str_father_cnic));
                String date = (cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));
                helpers.setDate(date);
                list.add(helpers);





            }
            while (cursor.moveToNext());
        }
        return list;
    }

    public List<ListHelper> attendancelist(String string) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + string + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()){
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    String str_father_cnic = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                    helpers.setFather_cnic(Integer.parseInt(str_father_cnic));
                    String date = (cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));
                    helpers.setDate(date);
                    list.add(helpers);

                }while (cursor.moveToNext());
            }


        }else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }



    public List<ListHelper> checkAttendance(String date) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + date + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()){
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    list.add(helpers);
                }while (cursor.moveToNext());
            }


        }else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }



    public List<ListHelper> father_cnic_data(String cnic) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_FATHER_CNIC = '" + cnic + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()){
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    list.add(helpers);
                }while (cursor.moveToNext());
            }


        }else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }





    public List<ListHelper> StudentAllRecordList() {

        List<ListHelper> list = new ArrayList<>();

        String query = "SELECT  * FROM STUDENT_TABLE";

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                ListHelper helpers = new ListHelper();
                String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                helpers.setStudent_name(name);
                String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                helpers.setStudent_class(str_class);
                String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                helpers.setStudent_attendec(str_attendance);
                String str_father_cnic = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                helpers.setFather_cnic(Integer.parseInt(str_father_cnic));
                String date = (cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));
                helpers.setDate(date);
                String str_image = (cursor.getString(cursor.getColumnIndex("IMAGE")));
                helpers.setImge_uri(str_image);
                list.add(helpers);

            }
            while (cursor.moveToNext());
        }
        return list;
    }


    public void UpdateAttendance(String roll_number, String attendance) {
        String whereClause = "STUDENT_ROLL_NUMBER = '" + roll_number + "'";
        ContentValues values = new ContentValues();
        values.put("STUDENT_ATTENDANCE", attendance);
        this.database.update("ATTENDANCE_TABLE", values, whereClause, null);
    }


    public void Delete(String roll_number) {

        Cursor cursor = this.database.rawQuery("SELECT * FROM STUDENT_TABLE WHERE STUDENT_ROLL_NUMBER = '" + roll_number + "' ", null);

        if (cursor.moveToFirst()) {
            this.database.delete("STUDENT_TABLE", "STUDENT_ROLL_NUMBER = '" + roll_number + "'", null);
            String strollnumber = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
            Toast.makeText(context, String.valueOf(strollnumber), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "No Exist", Toast.LENGTH_SHORT).show();
        }


    }


}
