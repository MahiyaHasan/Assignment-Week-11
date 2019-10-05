package com.example.mynavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerId);

        NavigationView navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.acceleratorMenuId)
        {
            Intent intent = new Intent(this,AccelerometerActivity.class);
            startActivity(intent);
        }

        else if(item.getItemId()== R.id.gyroscopeMenuId)
        {
            Intent intent = new Intent(this,GyroscopeActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.gyroscopeDataMenuId)
        {
            Intent intent = new Intent(this,GyroscopeDataActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.proximityMenuId)
        {
            Intent intent = new Intent(this, ProximityActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.shakeMenuId)
        {
            Intent intent = new Intent(this,ShakeDetectionActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.MapMenuId)
        {
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        }


        return false;
    }
}
