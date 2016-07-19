package com.example.bordo.rilevatoresvenimenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by bordo on 17/07/16.-
 */
public class ActivityStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic_activity);
       final TextView giornoSven=(TextView) findViewById(R.id.textView10);
       final CalendarView calendario= (CalendarView) findViewById(R.id.calendar);
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                giornoSven.setText("Svenimenti nel giorno "+calendario.getDate());

            }
        });

            }
    protected void onPause(){
        super.onPause();
        MainActivity.inApp=true;
    }

}
