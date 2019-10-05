package com.example.mynavigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<GyroData> {

    private Activity context;
    private List<GyroData> gyroDataList;

    public CustomAdapter(Activity context, List<GyroData> gyroDataList) {
        super(context, R.layout.samplelayout, gyroDataList);
        this.context = context;
        this.gyroDataList = gyroDataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.samplelayout,null,true);
        GyroData gyroData = gyroDataList.get(position);

        TextView t1 = view.findViewById(R.id.xGyro);
        TextView t2 = view.findViewById(R.id.yGyro);
        TextView t3 = view.findViewById(R.id.zGyro);

        t1.setText("X Value: "+gyroData.getX());
        t2.setText("Y Value:" +gyroData.getY());
        t3.setText("Z Value: "+gyroData.getZ());



        return view;
    }
}


