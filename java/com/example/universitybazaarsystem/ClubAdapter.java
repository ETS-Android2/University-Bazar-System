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
public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {
    private List<ClubModel> listData;

    public ClubAdapter(List<ClubModel> listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ClubAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clubs, parent, false);
        return new ClubAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubAdapter.MyViewHolder holder, int position) {
        ClubModel model = listData.get(position);
        holder.txtcname.setText(model.getClubName());
        holder.txtdes.setText(model.getDes());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtcname, txtcontact, txtdes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtcname = (TextView) itemView.findViewById(R.id.cnametxt);
            txtdes = (TextView) itemView.findViewById(R.id.destxt);


        }
    }

}

