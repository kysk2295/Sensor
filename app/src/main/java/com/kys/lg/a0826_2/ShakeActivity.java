package com.kys.lg.a0826_2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShakeActivity extends AppCompatActivity {

    Button btn;
    SensorManager sensorM;
    SensorEventListener sensorL;
    Sensor acc;

    int x,y,z;
    int speed;
    final int SHAKE=14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        btn=findViewById(R.id.btn);

        sensorM=(SensorManager)getSystemService(SENSOR_SERVICE);
        acc=sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorL=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
              x=(int)event.values[0];
              y=(int)event.values[1];
              z=(int)event.values[2];

              speed=x+y+z;
              if(speed>=SHAKE){
                  btn.setVisibility(View.VISIBLE);
              }

                Log.i("MY","speed: "+speed);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn.setVisibility(View.GONE);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorM.registerListener(sensorL,acc,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorM.unregisterListener(sensorL);
    }
}
