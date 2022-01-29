package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExchangeConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_confirm);
    }
    public void Return(View view)
    {
        Intent downloadIntent = new Intent(getApplicationContext(), ShowActivity.class);
        startActivity(downloadIntent);
    }
}