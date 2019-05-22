package com.kys.lg.a0826_2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrientationActivity extends AppCompatActivity {


    //방향계 센서 예제
    //센서 관련 객체들
    SensorManager sensorM;
    SensorEventListener sensorL;
    Sensor oriSensor;

    int heading,pitch,roll;

    TextView txt1,txt2,txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);

        //센서 매니저
        sensorM=(SensorManager)getSystemService(SENSOR_SERVICE);
        oriSensor=sensorM.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        //센서 감지자

        sensorL=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //센서 값이 변경될 때마다 호출되는 베서드
                heading=(int)event.values[0]; //0번 값이 방위값이다.(기기의 머리 부분이 어느방향을 가리키고 있는지 수치값으로 반환)
                //경사됴(기기의 수직 기울기)
                pitch=(int)event.values[1];
                //회전값(기기의 수평 기울기)
                roll=(int)event.values[2];

                txt1.setText("방위값: "+heading);
                txt2.setText("경사도: "+pitch);
                txt3.setText("회전값: "+roll);
                  }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //센서 반응 속도 변경시 호출(쓸일 거의 없음)
            }
        };


    }//onCreate()

    @Override
    protected void onResume() {
        super.onResume();
        //다른 화면에서 현재 화면으로 전환 되었다면 센서를 등록

        //registerListener(센서 감지자, 센서종류,반응 속도);
        sensorM.registerListener(sensorL,oriSensor,SensorManager.SENSOR_DELAY_FASTEST);

                //센서 반은 속도
                //SENSOR_DELAY_FASTEST
                //SENSOR_DELAY_GAME
                //SENSOR_DELAY_UI
                //SENSOR_DELAY_NORMAL

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorM!=null){
            //다른 화면으로 전환할 때 센서를 해제
            sensorM.unregisterListener(sensorL);
        }
    }
}
