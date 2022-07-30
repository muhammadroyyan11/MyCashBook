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

public class CashoutActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout dateOut, nominalOut, keteranganOut;
    SimpleDateFormat dateFormat;
    Button buttonSimpan;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        setContentView(R.layout.activity_cashout);
        dbHelper = new DatabaseHelper(this);
        nominalOut = findViewById(R.id.nominal_cashout);
        keteranganOut = findViewById(R.id.desc_cashout);
        buttonSimpan = findViewById(R.id.buttonSimpan);
        dateOut = findViewById(R.id.tanggal_cashout);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.forLanguageTag("INDONESIA"));

        dateOut.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.showDateDialog(CashoutActivity.this, dateOut, dateFormat);
            }
        });

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
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
        int status = 0;
        String tanggalOut = dateOut.getEditText().getText().toString();
        String nominal = nominalOut.getEditText().getText().toString();
        String keterangan = keteranganOut.getEditText().getText().toString();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_USER, user);
        values.put(DatabaseHelper.KEY_STATUS, status);
        values.put(DatabaseHelper.KEY_TANGGAL, tanggalOut);
        values.put(DatabaseHelper.KEY_NOMINAL, nominal);
        values.put(DatabaseHelper.KEY_KETERANGAN, keterangan);

        if (tanggalOut.equals(" ")||nominal.equals(" ")||keterangan.equals(" ")) {
            Toast.makeText(CashoutActivity.this, "Harap isi semua data!", Toast.LENGTH_SHORT).show();
        }else  {
            dbHelper.insertData(values);
            Toast.makeText(CashoutActivity.this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public void handleBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}