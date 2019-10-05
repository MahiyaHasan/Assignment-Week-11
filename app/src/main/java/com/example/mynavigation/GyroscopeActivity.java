package com.example.mynavigation;


        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.hardware.SensorManager;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    private TextView xGyro, yGyro, zGyro;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        databaseReference = FirebaseDatabase.getInstance().getReference("History");
        mySensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        xGyro = (TextView) findViewById(R.id.xGyro);
        yGyro = (TextView) findViewById(R.id.yGyro);
        zGyro = (TextView) findViewById(R.id.zGyro);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

       if(x>=-1 && x<=1 && z>=-1 && z<=1) {
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            ////////chaile toast korao jay

           String x1 = Float.toString(x).trim();
           String y1 = Float.toString(y).trim();
           String z1 = Float.toString(z).trim();
           String key= databaseReference.push().getKey();

           GyroData gyroData = new GyroData(x1,y1,z1);
           databaseReference.child(key).setValue(gyroData);
           Toast.makeText(GyroscopeActivity.this,"Horizontal position, data inserted.",Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);

        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        }

        xGyro.setText("X: " + (int) x);
        yGyro.setText("Y: " + (int) y);
        zGyro.setText("Z: " + (int) z);

    }

    private void savedata() {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    protected void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
}