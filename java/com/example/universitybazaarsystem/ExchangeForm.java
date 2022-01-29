package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExchangeForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_form);
    }
    public void onSubmit(View view)
    {
        Intent downloadIntent = new Intent(getApplicationContext(), ExchangeConfirm.class);
        startActivity(downloadIntent);
    }
}