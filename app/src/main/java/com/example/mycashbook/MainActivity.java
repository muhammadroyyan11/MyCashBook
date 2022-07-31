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

        uangIn = findViewById(R.id.uangMasuk);
        uangOut = findViewById(R.id.uangOut);
        pengaturan = findViewById(R.id.pengaturan);
        cashIn = findViewById(R.id.tambahCashIn);
        cashOut = findViewById(R.id.tambahCashOut);
        detailCash = findViewById(R.id.buttonDetail);
        databaseHelper = new DatabaseHelper(this);

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