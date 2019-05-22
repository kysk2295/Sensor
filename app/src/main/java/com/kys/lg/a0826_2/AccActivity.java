package com.kys.lg.a0826_2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccActivity extends AppCompatActivity {

    TextView text1,text2,text3;
    SensorManager sensorM;
    SensorEventListener sensorL;
    Sensor accSensor;

    int x,y,z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);

        sensorM=(SensorManager)getSystemService(SENSOR_SERVICE);
        //가속도 센서
        accSensor=sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorL=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //x축 가속도 값(가로로 움직이는 값)
                x=(int)event.values[0];

                //y축 가속도 값
                y=(int)event.values[1];

                //z축 가속도 값
                z=(int)event.values[2];

                text1.setText("x: "+x);
                text2.setText("y: "+y);
                text3.setText("z: "+z);

            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //민감도 변경(거의 없음)
            }
        };
    }//onCreate()

    @Override
    protected void onResume() {
        super.onResume();
        sensorM.registerListener(sensorL,accSensor,SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorM.unregisterListener(sensorL);
    }

}
