package com.example.mynavigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.seismic.ShakeDetector;
public class ShakeDetectionActivity extends AppCompatActivity implements ShakeDetector.Listener{
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_detection);
        SensorManager SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        ShakeDetector SD=new ShakeDetector(this);
        SD.start(SM);

    }
    @Override
    public void hearShake() {
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        makePhoneCall();

    }

    private void makePhoneCall() {

        String number = "01674894083";
        if(ContextCompat.checkSelfPermission(ShakeDetectionActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShakeDetectionActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else
            {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // no need to call onpause and onresume for shake detector.
}