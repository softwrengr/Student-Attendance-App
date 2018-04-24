package com.example.attaurrahman.studentattendence.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.attaurrahman.studentattendence.Activities.MainActivity;
import com.example.attaurrahman.studentattendence.DataBase.Crud;
import com.example.attaurrahman.studentattendence.DataBase.DBStudent;
import com.example.attaurrahman.studentattendence.DataBase.ListHelper;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class StudentDetail extends Fragment {

    View parentView;
    Crud crud;

    String str_student_name, str_student_roll_number, str_student_class, str_student_father_cnic, str_image,str_date;
    ListView lvItems;
    List<String> list;
    ArrayAdapter arrayAdapter;
    @BindView(R.id.btnSave) Button btnSave;
    @BindView(R.id.etStudentName)EditText etStudentName;
    @BindView(R.id.etClass)EditText etClass;
    @BindView(R.id.etRollNumber)EditText etRollNumber;
    @BindView(R.id.etFatherCNIC)EditText etFatherCNIC;


    @BindView(R.id.iv_add_image)ImageView ivAddImage;


    private DBStudent dbStudent;
    private SQLiteDatabase mDb;


    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int SELECT_PICTURE = 1;
    String fileManagerString,imagePath;
    String selectedImagePath="";
    int columnIndex;
    CommonClass commClass;

    String complaint_image;

    Uri image_uri;




    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_student_detail, container, false);

        crud = new Crud(getActivity());
        ButterKnife.bind(this, parentView);


        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            } });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etStudentName.length()<=0){
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                }else if (etRollNumber.length()<=0){
                    Toast.makeText(getActivity(), "Enter Roll Number", Toast.LENGTH_SHORT).show();
                }else if (etClass.length()<=0){
                    Toast.makeText(getActivity(), "Enter Class", Toast.LENGTH_SHORT).show();
                }else {

                    str_image = image_uri.toString();
                    str_student_name = etStudentName.getText().toString();
                    str_student_roll_number = etRollNumber.getText().toString();
                    str_student_class = etClass.getText().toString();
                    str_student_father_cnic = etFatherCNIC.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
                    str_date = mdformat.format(calendar.getTime());
                    crud.insertData(str_student_name, str_student_roll_number, str_student_class, str_student_father_cnic,null,str_date,str_image);

                }}
        });


        return parentView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {


            case 0:
                if (resultCode == RESULT_OK) {


                    Bitmap bm = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                    File sourceFile = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".jpg");
                    FileOutputStream fo;
                    try {
                        sourceFile.createNewFile();
                        fo = new FileOutputStream(sourceFile);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ivAddImage.setImageBitmap(bm);

                    complaint_image = sourceFile.getAbsolutePath().toString();



                } else {

                    Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    image_uri = imageReturnedIntent.getData();
                    ivAddImage.setImageURI(image_uri);

                    complaint_image = getImagePath(image_uri);

                } else {
                    Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    public String getImagePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

}
