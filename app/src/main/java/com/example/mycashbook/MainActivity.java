package com.example.mycashbook;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mycashbook.activities.CashflowActivity;
import com.example.mycashbook.activities.CashinActivity;
import com.example.mycashbook.activities.CashoutActivity;
import com.example.mycashbook.activities.OptionsActivity;
import com.example.mycashbook.javaclass.CashModel;
import com.example.mycashbook.javaclass.CustomCursorAdapter;
import com.example.mycashbook.javaclass.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton pengaturan, cashIn, cashOut, detailCash;
    DatabaseHelper databaseHelper;
    TextView uangIn, uangOut;
    private ArrayList<CashModel> cashModelArrayList;
    private CustomCursorAdapter customCursor;


//    private ArrayList<CashModel> cashModelArrayList;

//    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }

//        cashModelArrayList = new ArrayList<>();
//        cashModelArrayList  = databaseHelper.readCash();


        uangIn = findViewById(R.id.uangMasuk);
        uangOut = findViewById(R.id.uangOut);
        pengaturan = findViewById(R.id.pengaturan);
        cashIn = findViewById(R.id.tambahCashIn);
        cashOut = findViewById(R.id.tambahCashOut);
        detailCash = findViewById(R.id.buttonDetail);
        databaseHelper = new DatabaseHelper(this);

//        mChart = (LineChart) findViewById(R.id.lineChart);
//
//
//        mChart.animateXY(100, 500);

//        cashModelArrayList = new ArrayList<>();
//        customCursor = new CustomCursorAdapter(cashModelArrayList, MainActivity.this);
//
//        cashModelArrayList  = databaseHelper.readCash();


        pengaturan.setOnClickListener(this);
        cashIn.setOnClickListener(this);
        cashOut.setOnClickListener(this);
        detailCash.setOnClickListener(this);
        
        Integer totalPemasukan = databaseHelper.total("1");
        Integer totalPengeluaran = databaseHelper.total("0");

//        Integer uangMasuk = databaseHelper.uang("1");
//        Integer uangKeluar = databaseHelper.uang("0");

//        Cursor cursor = databaseHelper.getChart(1);

        uangIn.setText("Rp. "+String.valueOf(totalPemasukan));
        uangOut.setText("Rp. "+String.valueOf(totalPengeluaran));
    }


//    private void setData(int count, int range){
//
////        ArrayList<Entry> yVals = new ArrayList<>();
////        cashModelArrayList  = databaseHelper.readCash();
////        ArrayList<Entry> kasus = new ArrayList<>();
////        kasus.add(new Entry(10F, 149F));
////        kasus.add(new Entry(11F, 113F));
////        kasus.add(new Entry(12F, 20000F));
////        kasus.add(new Entry(13F, 20000F));
////        kasus.add(new Entry(14F, 5000F));
////        kasus.add(new Entry(15F, 5000F));
////        kasus.add(new Entry(20F, 70000F));
////        kasus.add(new Entry(25F, 26400F));
////        kasus.add(new Entry(26F, 10500F));
////        kasus.add(new Entry(27F, 50500F));
////        kasus.add(new Entry(27F, 30500F));
////        kasus.set(databaseHelper.getData(1));
//
////        ArrayList<Entry> sembuh = new ArrayList<>();
////        sembuh.add(new Entry(10F, 300F));
////        sembuh.add(new Entry(11F, 2000F));
////        sembuh.add(new Entry(12F, 2200F));
////        sembuh.add(new Entry(13F, 1600F));
////        sembuh.add(new Entry(14F, 1000F));
////        sembuh.add(new Entry(15F, 2000F));
////        sembuh.add(new Entry(20F, 300F));
////        sembuh.add(new Entry(25F, 15500F));
////        sembuh.add(new Entry(26F, 100000F));
////        sembuh.add(new Entry(27F, 40000F));
////        sembuh.add(new Entry(27F, 10000F));
//
////        ArrayList<Entry> yVals2 = new ArrayList<>();
////        for (int i = 0; i<count; i++){
////            int val = Integer.valueOf(String.valueOf(cashModelArrayList));
////            kasus.add(new Entry(i, val));
//////            kasus.set(i, data);
////        }
////
////        ArrayList<Entry> yVals3 = new ArrayList<>();
////        for (int i = 0; i<count; i++){
////            float val = (float) (Math.random()*range)+250;
////            yVals3.add(new Entry(i, val));
////        }
//
//        LineDataSet set1, set2, set3;
////
//        set1 = new LineDataSet(kasus, "PEMASUKAN");
//        set1.setColor(Color.GREEN);
//        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
////        set1.setCircleRadius(5f);
//        set1.setDrawCircles(true);
//        set1.setLineWidth(3f);
//        set1.setCircleColor(Color.WHITE);
//
//        set2 = new LineDataSet(sembuh, "PENGERLUARAN");
////        set3 = new LineDataSet(yVals3, "Data 3");
//        set2.setColor(Color.RED);
//        set2.setDrawCircles(true);
//        set2.setLineWidth(3f);
//        set2.setCircleColor(Color.WHITE);

//        LineData data = new LineData(set1, set2);
//        mChart.setData(data);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tambahCashIn:
                startActivity(new Intent(MainActivity.this, CashinActivity.class));
                break;
            case R.id.tambahCashOut:
                startActivity(new Intent(MainActivity.this, CashoutActivity.class));
                break;
            case R.id.buttonDetail:
                startActivity(new Intent(MainActivity.this, CashflowActivity.class));
                break;
            case R.id.pengaturan:
                startActivity(new Intent(MainActivity.this, OptionsActivity.class));
                break;
        }
    }
}