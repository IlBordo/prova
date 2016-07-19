package com.example.bordo.rilevatoresvenimenti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bordo on 17/07/16.
 */
public class ActivitySOS extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sos_activity);
    }
    protected void onPause(){
        super.onPause();
        MainActivity.inApp=true;
    }

}
