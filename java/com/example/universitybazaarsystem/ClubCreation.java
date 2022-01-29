package com.example.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ClubCreation extends AppCompatActivity
{
    // Declare an instance of FirebaseAuth
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://universitybazaarsystem-d0619-default-rtdb.firebaseio.com/");
    private DatabaseReference root = db.getReference().child("FormClub");
    HashMap<String, String> clubMap = new HashMap<>();
    //actionbar
    //private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_creation);



        Button back;

        back = findViewById(R.id.btn_close);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ClubHomePage.class));
            }
        });

    }
    public void btnCreate(View view)
    {
        final EditText clubName, clubDescription, contactInfo;
        String name,des,contact;
        clubName = findViewById(R.id.ClubNameInput);
        clubDescription = findViewById(R.id.ClubDescription);
        contactInfo = findViewById(R.id.ContactInfo);

        name = clubName.getText().toString();
        des = clubDescription.getText().toString();
        contact = contactInfo.getText().toString();

        clubMap.put("clubName", name);
        clubMap.put("des", des);
        clubMap.put("contact", contact);
        root.push().setValue(clubMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ClubCreation.this, "Club Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ClubHomePage.class));
            }
        });

    }



}