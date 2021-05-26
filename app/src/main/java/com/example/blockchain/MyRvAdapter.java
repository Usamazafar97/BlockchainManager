package com.example.blockchain;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {
    List<Block> ls;
    Context c;
    public MyRvAdapter(List<Block> ls, Context c) {
        this.c=c;
        this.ls=ls;
    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, final int position) {

        holder.date.setText("Date: "+ls.get(position).getDate());
        holder.data.setText("Rs "+ls.get(position).getData());
        holder.previousHash.setText("Previous Hash: "+ls.get(position).getPreviousHash());
        holder.currentHash.setText("Current Hash: "+ls.get(position).getHash());
        // holder.address.setText(ls.get(position).getAddress());


//        holder.rowww.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(c,position+"hello",Toast.LENGTH_SHORT).show();
//                //startActivity(new Intent(MyRvAdapter.this,infoActivity.class));
//
//                Intent intent = new Intent(c,infoActivity.class);
//                intent.putExtra("name",ls.get(position).getName());
//                intent.putExtra("phoneNo",ls.get(position).getPhno());
//                intent.putExtra("email",ls.get(position).getEmail());
//                intent.putExtra("address",ls.get(position).getAddress());
//                c.startActivity(intent);
//            }
//
//        });
    }
    public void setContactList( List<Block> contactList){
        // ls.clear();
        ls = contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data,date,previousHash,currentHash;
        RelativeLayout rowww;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data=itemView.findViewById(R.id.data);
            date=itemView.findViewById(R.id.date);
            previousHash=itemView.findViewById(R.id.previousHash);
            currentHash=itemView.findViewById(R.id.currentHash);
            rowww=itemView.findViewById(R.id.rowww);
        }
    }
}

