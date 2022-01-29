package com.example.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button checkout;
    private TextView txtTotalAmount,txtGrandTotal;
    FirebaseAuth F_auth;
    private int overTotalPrice=0;
    private double grandTotal=0;

    ArrayList<CartModel> cartList;
    CartModel model;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        checkout=(Button)findViewById(R.id.btnCheckOut);




    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart");
        F_auth=FirebaseAuth.getInstance();
        FirebaseRecyclerOptions<CartModel> options=
                new FirebaseRecyclerOptions.Builder<CartModel>()
                .setQuery(cartListRef.child(F_auth.getCurrentUser().getUid()),CartModel.class).build();
        FirebaseRecyclerAdapter<CartModel,CartViewHolder> adapter
                =new FirebaseRecyclerAdapter<CartModel, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull CartModel cartModel) {
                holder.txtProductName.setText(cartModel.getName());
                holder.txtProductPrice.setText(cartModel.getPrice());
                int oneTypeProduct=(Integer.valueOf(cartModel.getPrice()));
                overTotalPrice=overTotalPrice+oneTypeProduct;
                grandTotal=overTotalPrice*1.5;
                txtGrandTotal=(TextView)findViewById(R.id.gTotal);
                txtTotalAmount=(TextView)findViewById(R.id.totalPrice);
                txtTotalAmount.setText(String.valueOf(overTotalPrice));
                txtGrandTotal.setText(String.valueOf(grandTotal));
                deleteItemFromShow(cartModel.getName());

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();




    }

    public void onBtnCheckOut(View view)
    {






        //Delete cart
        F_auth=FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference()
                .child("Cart").child(F_auth.getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Cart.this,"FINAL ORDER PLACED",Toast.LENGTH_SHORT).show();



                    Intent intent=new Intent(Cart.this, Payments.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

    }
    public void  deleteItemFromShow(String name)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance("\"https://universitybazaarsystem-d0619-default-rtdb.firebaseio.com/").getReference();
        Query applesQuery = ref.child("Sales").orderByChild("itemName").equalTo(name);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
    public void onbtnBackClick(View view)
    {
        startActivity(new Intent(Cart.this, HomeCart.class));
    }
}