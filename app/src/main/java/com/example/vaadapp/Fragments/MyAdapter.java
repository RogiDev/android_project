package com.example.vaadapp.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaadapp.Models.Apartment;
import com.example.vaadapp.Models.Payments;
import com.example.vaadapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Apartment> list;
    Context context;

    public MyAdapter(Context context, ArrayList<Apartment> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Apartment apartment = list.get(position);
        holder.userIdRe.setText(apartment.getApartmentNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userIdRe, amountRe, monthRe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdRe=itemView.findViewById(R.id.userIdText);
        }
    }

//    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView view;
//        public RecyclerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            view = itemView.findViewById(R.id.randomText);
//        }
//
//        public TextView getView(){
//            return view;
//        }
//    }
}

