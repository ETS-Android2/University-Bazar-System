package com.example.universitybazaarsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {

    private List<SalesModel> listData;

    public SalesAdapter(List<SalesModel> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalesModel model = listData.get(position);
        holder.txtsname.setText(model.getSellerName());
        holder.txtiname.setText(model.getItemName());
        holder.txtprice.setText(model.getItemPrice());




    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtiname, txtsname, txtprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtiname = (TextView) itemView.findViewById(R.id.inametxt);
            txtsname = (TextView) itemView.findViewById(R.id.snametxt);
            txtprice = (TextView) itemView.findViewById(R.id.pricetxt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(),ItemDetails.class);
                    SalesModel model = listData.get(getAdapterPosition());
                    i.putExtra("detailSname",model.getSellerName());
                    i.putExtra("detailIname",model.getItemName());
                    i.putExtra("detailIprice",model.getItemPrice());
                    i.putExtra("detailImg",model.getmImageUrl());
                    i.putExtra("detailDesc",model.getDescription());
                    i.putExtra("detailLoc",model.getLocation());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}