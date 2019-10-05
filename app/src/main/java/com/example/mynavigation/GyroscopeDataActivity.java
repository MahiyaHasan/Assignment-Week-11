package com.example.mynavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GyroscopeDataActivity extends AppCompatActivity {
    private ListView listview;
    DatabaseReference databaseReference;
    private List<GyroData> gyroDataList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_data);

        databaseReference = FirebaseDatabase.getInstance().getReference("History");
        gyroDataList =  new ArrayList<>();
        customAdapter = new CustomAdapter(GyroscopeDataActivity.this,gyroDataList);

        listview = findViewById(R.id.gyrodata_lv);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gyroDataList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    GyroData history = dataSnapshot1.getValue(GyroData.class);
                    gyroDataList.add(history);
                }
                listview.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
