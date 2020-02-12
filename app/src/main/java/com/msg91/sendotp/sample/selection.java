package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selection extends AppCompatActivity {
    Button btn;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        sh=getSharedPreferences("Official1",MODE_PRIVATE);
        SharedPreferences.Editor e=sh.edit();
        e.putBoolean("ph",true);
        e.apply();
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(selection.this,booking.class);
                startActivity(i);
            }
        });
    }}