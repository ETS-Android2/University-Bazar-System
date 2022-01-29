package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cart);
    }
    public void onBtnBuy(View view)
    {
        startActivity(new Intent(HomeCart.this, Cart.class));
    }
    public void onBtnLend(View view)
    {
        startActivity(new Intent(HomeCart.this, ExchangeCart.class));
    }
    public void onBtnHome(View view)
    {
        startActivity(new Intent(HomeCart.this, ShowActivity.class));
    }
}