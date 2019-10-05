package com.example.mynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {/////////porpor duita implement korte hobe dorkar hoile, jehetu Shakedetector ta

    private TextView xtext,ytext,ztext;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {///////sharakkhin sensor on thakle battery khabe, tai
        ///////////////////////////////////////////////on create e shudhu on rakhbo sensor/ register korbo.
        //////////////on pause e unregister kore dibo
        ////////////on resume e abar register korabo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);///////instance toiri korlam
        ////////SM diyei sensor register, unregister hobe

        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ///////////////////////// onno shob khetre ei TYPE_ACCELEROMETER er jaygay onno nam boshbe
        SM.registerListener(this,mySensor, SensorManager.SENSOR_DELAY_NORMAL);
/////////parameter 1 : koi sensor on korte chai, main activity tei, so ekhane hobe, parameter 2: kake diye access korbo, parameter 3: kotokkhon delay hobe

        xtext= (TextView)findViewById(R.id.xtext_tv);
        ytext= (TextView)findViewById(R.id.ytext_tv);
        ztext= (TextView)findViewById(R.id.ztext_tv);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {////////sensor data change hole ja korte chai
        int x = (int) (sensorEvent.values[0]);//////////////eikhane passed object ta hocche sensorEvent, tai eta.values ashche
        int y = (int) (sensorEvent.values[1]);
        int z = (int) (sensorEvent.values[2]);

        ///////////built in bhabe 3 ta value dey acceleretormeter , tai 0,1,2 diye access kori.
        ////////////proximity 1 tai dey, only 0 dibe

        xtext.setText("X is: " + x);
        ytext.setText("Y is: " + y);
        ztext.setText("Z is: " + z);



        if(y==-9) {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            ////////chaile toast korao jay

        }
        else if(y== 9) {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            ////////chaile toast korao jay

        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {//////laagbe na, but empty override kora lagbe

    }

    @Override

    protected void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SM.unregisterListener(this);
    }


}
