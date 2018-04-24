package com.example.attaurrahman.studentattendence.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.example.attaurrahman.studentattendence.Fragment.MainLoginFragment;
import com.example.attaurrahman.studentattendence.GenralUtills.Utilities;
import com.example.attaurrahman.studentattendence.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Utilities.connectFragmentWithOutBackStack(this,new MainLoginFragment());


    }

}
