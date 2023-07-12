package com.tanuwijaya_202102261.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


public class MenuActivity extends AppCompatActivity {


    private Button _masterMahasiswaButton;
    private Intent _intent;

    private Button _forexButton;

    private Button _CuacaButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        _masterMahasiswaButton = findViewById(R.id.masterMahasiswaButton);
        _forexButton = findViewById(R.id.forexButton);
        _CuacaButton = findViewById(R.id.cuacaButton);

        _masterMahasiswaButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                _intent = new Intent(getApplicationContext(), ViewMasterActivity.class);
                startActivity(_intent);
            }
        });

        _forexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent = new Intent(getApplicationContext(), ForexActivity.class);
                startActivity(_intent);
            }
        });

        _CuacaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _intent = new Intent(getApplicationContext(), CuacaMainActivity.class);
                startActivity(_intent);
            }
        });
    }
}