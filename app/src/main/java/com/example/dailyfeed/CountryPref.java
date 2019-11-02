package com.example.dailyfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CountryPref extends AppCompatActivity  {

    Spinner spinner;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_countries);
        spinner = findViewById(R.id.spinnerCountry);
        sp = getSharedPreferences("countrycode", Context.MODE_PRIVATE);
        editor = sp.edit();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                Log.i("YAY","SPINNER ITEM-->"+item);
               editor.putString("countryname", String.valueOf(item));
               editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
