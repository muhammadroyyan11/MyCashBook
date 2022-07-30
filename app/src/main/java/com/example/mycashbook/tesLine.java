package com.example.mycashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class tesLine extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_line);

        mChart = (LineChart) findViewById(R.id.lineChart);

        setData(40, 60);
        mChart.animateX(1000);
    }

    private void setData(int count, int range){

        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i<count; i++){
            float val = (float) (Math.random()*range)+250;
            yVals.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i<count; i++){
            float val = (float) (Math.random()*range)+250;
            yVals2.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<>();
        for (int i = 0; i<count; i++){
            float val = (float) (Math.random()*range)+250;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals, "Data 1");
        set2 = new LineDataSet(yVals2, "Data 2");
        set3 = new LineDataSet(yVals3, "Data 3");

        LineData data = new LineData(set1, set2, set3);
        mChart.setData(data);
    }
}