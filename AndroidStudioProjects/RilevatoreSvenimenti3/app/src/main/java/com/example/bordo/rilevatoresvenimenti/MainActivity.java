package com.example.bordo.rilevatoresvenimenti;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.widget.Toast;
import android.content.Context;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    int i = 1;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float lastAcc = 0.0f;
    private float acceleration = 0.0f;
    private float totAcc = 0.0f;
    private boolean onEvent = false;
    boolean popSven = true;
    boolean attivato=false;
    boolean vibrazione=true;
    boolean suoneria=true;
    static public boolean inApp=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscroll);
        //Sensore
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastAcc=SensorManager.GRAVITY_EARTH;
        acceleration=SensorManager.GRAVITY_EARTH;


        //Bottone SOS

        Button sos = (Button) findViewById(R.id.button);
        final TextView sven = (TextView) findViewById(R.id.textView);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                         inApp=false;
                         modificaSven();
                         Intent sos=new Intent(MainActivity.this,ActivitySOS.class);

                         startActivity(sos);


            }
        });

       TextView impostazioni=(TextView) findViewById(R.id.textView2);
        impostazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inApp=false;
                Intent i=new Intent(MainActivity.this, ActivitySettings.class);
                startActivity(i);

            }
        });

        TextView statistiche=(TextView) findViewById(R.id.textView5);
        statistiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inApp=false;
                Intent i2=new Intent(MainActivity.this, ActivityStatistics.class);
                startActivity(i2);

            }
        });

        final Switch vibraz= (Switch) findViewById(R.id.switch2);
        vibraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(70);
                if(vibrazione){
                    vibrazione=false;
                }else{
                    vibrazione=true;
                }

            }
        });

        final Switch suono= (Switch) findViewById(R.id.switch3);
        suono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(70);
                if(suoneria){
                    suoneria=false;
                }else{
                    suoneria=true;
                }

            }
        });


    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
      super.onPause();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //Bottone Attivato
        Switch switchattivato = (Switch) findViewById(R.id.switch1);
        if(switchattivato.isChecked() && inApp){



            Context context = getApplicationContext();

            //Toast che informa l'utente sull'app in background

            int duration = Toast.LENGTH_LONG;

            CharSequence text = "L'app continuerà a funzionare in background";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            CharSequence text2 = "E monitorerà i tuoi svenimenti";
            Toast toast2 = Toast.makeText(context, text2, duration);
            toast2.show();
        }else if(!switchattivato.isChecked() && !inApp){
            Context context = getApplicationContext();

            //Toast che informa l'utente sull'app in background

            int duration = Toast.LENGTH_LONG;

            CharSequence text = "L'app non continuerà a funzionare in background";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }



    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {   }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if (!onEvent)
        {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            lastAcc = acceleration;
            acceleration = x*x+y*y+z*z;
            float diff = acceleration - lastAcc;
            totAcc = diff*acceleration;
            if (totAcc>100000)
            {


                NotificationCompat.Builder n  = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setContentTitle("Sei svenuto?")
                        .setContentText("Tocca entro 10 secondi per annullare SOS")
                        .setSmallIcon(android.R.drawable.ic_dialog_email);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, n.build());

                notificaSec();
                aggiungiSven();


            }
        }

    }

    private void aggiungiSven(){
        i++;
        modificaSven();
    }

    public void modificaSven(){
        final TextView sven = (TextView) findViewById(R.id.textView);
        sven.setText("Svenimenti oggi : " + numero());
    }

    public void notificaSec(){

        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        CharSequence text = "Rilevato svenimento,entrerò in modalita SOS tra 10 secondi";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

     new CountDownTimer(10000, 1000) {

         public void onTick(long millisUntilFinished) {
                //  mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                CharSequence text = "Rilevato svenimento,entrerò in modalita SOS tra "+millisUntilFinished/1000+" secondi";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

            public void onFinish() {
                popupSven();
                notificaSven();
            }
        }.start();


    }

    private void notificaSven(){
        NotificationCompat.Builder n  = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle("Sei svenuto")
                .setContentText("Stiamo chiamando aiuto..")
                .setSmallIcon(android.R.drawable.ic_dialog_email);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n.build());
    }

    private void popupSven() {

        // Vibrazione -> La funzione vibrate indica la durata
        if(vibrazione) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(4000);
        }

        if (popSven) {
            popSven=false;
            AlertDialog.Builder svenimento = new AlertDialog.Builder(this);

            svenimento.setTitle("Rilevato svenimento");
            svenimento.setMessage("Tutto ok?");
            svenimento.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                        }
                    });

            svenimento.show();




        }
    }

    public int numero() {
        return i++;

    }

}