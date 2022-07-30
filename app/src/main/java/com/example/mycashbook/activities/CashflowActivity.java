package com.example.mycashbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mycashbook.R;
import com.example.mycashbook.javaclass.CashModel;
import com.example.mycashbook.javaclass.CustomCursorAdapter;
import com.example.mycashbook.javaclass.DatabaseHelper;

import java.util.ArrayList;

public class CashflowActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private ArrayList<CashModel> cashModelArrayList;
    private CustomCursorAdapter customCursor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashflow);
//        setTitle("Detail Cash Flow");
        cashModelArrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(CashflowActivity.this);

        cashModelArrayList  = databaseHelper.readCash();

        customCursor = new CustomCursorAdapter(cashModelArrayList, CashflowActivity.this);
        recyclerView = findViewById(R.id.idRVCash);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CashflowActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customCursor);
    }
}