package com.example.universitybazaarsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClubHomePage extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home_page);

        // Actionbar and its title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Clubs");

        //enable back button
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        Button Discover, Create, MyClubs;
        Discover = (Button) findViewById(R.id.discoverButton);
        Create = (Button) findViewById(R.id.createButton);

        Button back;

        back = findViewById(R.id.btn_close);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        Discover.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Toast.makeText(ClubHomePage.this, "Going to club search page..", Toast.LENGTH_SHORT).show();
                Intent downloadIntent = new Intent(getApplicationContext(), ViewClubs.class);
                startActivity(downloadIntent);
            }
        });

        Create.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // go to club creation page
                Intent downloadIntent = new Intent(getApplicationContext(), ClubCreation.class);
                startActivity(downloadIntent);
            }
        });



    }
}