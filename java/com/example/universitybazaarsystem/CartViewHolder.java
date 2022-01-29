package com.example.universitybazaarsystem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public TextView txtProductName,txtProductPrice;
    private AdapterView.OnItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName=itemView.findViewById(R.id.pName);
        txtProductPrice=itemView.findViewById(R.id.pPrice);

    }


}
