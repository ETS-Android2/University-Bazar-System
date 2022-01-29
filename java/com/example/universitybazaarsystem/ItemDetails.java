package com.example.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.internal.$Gson$Preconditions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ItemDetails extends AppCompatActivity {
    TextView txtItemName,txtSellerName,txtPriceDetail,txtDescription,txtLocation;
    String itemName,itemPrice,dlocation;
    FirebaseAuth F_auth;
    private Button addToCartButton;
    private Button addToExCartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        txtItemName=(TextView)findViewById(R.id.viewItemName);
        txtSellerName=(TextView)findViewById(R.id.viewSellerName);
        txtPriceDetail=(TextView)findViewById(R.id.viewPriceDetail);
        txtDescription=(TextView)findViewById(R.id.viewDescription);
        txtLocation=(TextView)findViewById(R.id.viewLocation);


        txtItemName.setText(getIntent().getStringExtra("detailIname"));
        txtSellerName.setText(getIntent().getStringExtra("detailSname"));
        txtPriceDetail.setText(getIntent().getStringExtra("detailIprice"));
        txtDescription.setText(getIntent().getStringExtra("detailDesc"));
        txtLocation.setText(getIntent().getStringExtra("detailLoc"));

        addToCartButton=(Button) findViewById(R.id.btnAddCart);
        addToExCartButton=(Button)findViewById(R.id.btnAddtoExCart);
        itemName=getIntent().getStringExtra("detailIname");
        itemPrice=getIntent().getStringExtra("detailIprice");
        dlocation=getIntent().getStringExtra("detailLoc");

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCartList();
            }
        });

        addToExCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToExCartList();
            }
        });


    }

    private void addingToExCartList(){
        final FirebaseDatabase db = FirebaseDatabase.getInstance("https://universitybazaarsystem-d0619-default-rtdb.firebaseio.com/");
        F_auth=FirebaseAuth.getInstance();
        final DatabaseReference root = db.getReference().child("ExchangeCart").child(F_auth.getCurrentUser().getUid());
        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("Name",itemName);
        cartMap.put("Price",itemPrice);
        root.push().setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ItemDetails.this, "Added to Cart.", Toast.LENGTH_SHORT).show();



                    startActivity(new Intent(ItemDetails.this, ExchangeCart.class));
                }

            }
        });


    }
    private void addingToCartList()
    {


        final FirebaseDatabase db = FirebaseDatabase.getInstance("https://universitybazaarsystem-d0619-default-rtdb.firebaseio.com/");
        F_auth=FirebaseAuth.getInstance();
        // Getting a child called users to make more systematic
        final DatabaseReference root = db.getReference().child("Cart").child(F_auth.getCurrentUser().getUid());

        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("Name",itemName);
        cartMap.put("Price",itemPrice);


        root.push().setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ItemDetails.this, "Added to Cart.", Toast.LENGTH_SHORT).show();



                    startActivity(new Intent(ItemDetails.this, Cart.class));
                }

            }
        });
    }

    public void onBtnBack(View view)
    {
        startActivity(new Intent(ItemDetails.this, ShowActivity.class));
    }

}