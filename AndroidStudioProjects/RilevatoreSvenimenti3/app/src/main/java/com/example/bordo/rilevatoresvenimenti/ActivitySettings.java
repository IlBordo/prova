package com.example.bordo.rilevatoresvenimenti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class ActivitySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    protected void onPause(){
        super.onPause();
        MainActivity.inApp=true;
    }

}
