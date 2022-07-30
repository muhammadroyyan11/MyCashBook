package com.example.mycashbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycashbook.MainActivity;
import com.example.mycashbook.R;
import com.example.mycashbook.javaclass.DatabaseHelper;
import com.example.mycashbook.javaclass.DatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CashinActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout dateInput, nominal, keterangan;
    SimpleDateFormat dateFormat;
    Button simpan;


    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        setContentView(R.layout.activity_cashin);
        dbHelper = new DatabaseHelper(this);
        dateInput = findViewById(R.id.tanggal_cashin);
        nominal = findViewById(R.id.nominal_cashin);
        keterangan = findViewById(R.id.desc_cashin);
        simpan = findViewById(R.id.simpan_cashin);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.forLanguageTag("INDONESIA"));

        dateInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.showDateDialog(CashinActivity.this, dateInput, dateFormat);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSQLite();
            }
        });
    }

    private void saveSQLite() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String prefUsername = prefs.getString("username", "No name defined");
        String user = prefUsername;
        int status = 1;
        String tanggal = dateInput.getEditText().getText().toString();
        String nominalIn = nominal.getEditText().getText().toString();
        String keteranganIn = keterangan.getEditText().getText().toString();

        ContentValues values =  new ContentValues();
        values.put(DatabaseHelper.KEY_USER, user);
        values.put(DatabaseHelper.KEY_STATUS, status);
        values.put(DatabaseHelper.KEY_TANGGAL, tanggal);
        values.put(DatabaseHelper.KEY_NOMINAL, nominalIn);
        values.put(DatabaseHelper.KEY_KETERANGAN, keteranganIn);

        if (tanggal.equals("") || nominalIn.equals("") || keteranganIn.equals("")) {
            Toast.makeText(CashinActivity.this, "Harap isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.insertData(values);
            Toast.makeText(CashinActivity.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void handleBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}