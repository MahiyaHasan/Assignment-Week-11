package com.example.mynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class ProximityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        SensorManager sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        final Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0]<proximitySensor.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);
                }
                else
                {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);

                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };

        sensorManager.registerListener(sensorEventListener,proximitySensor,2 * 1000 * 1000);



    }

    public void vibration(View view){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(60);
    }
}
