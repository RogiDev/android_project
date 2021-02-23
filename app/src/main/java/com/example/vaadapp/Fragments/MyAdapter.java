package com.example.vaadapp.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaadapp.Models.Payments;
import com.example.vaadapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Payments> payList;
    Context context;

    public MyAdapter(Context context, ArrayList<Payments> payList){
        this.context=context;
        this.payList=payList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Payments payment = payList.get(position);
        holder.userIdRe.setText(payment.getUserId());
        holder.amountRe.setText(payment.getAmonut());
        holder.monthRe.setText(payment.getMonth());
    }

    @Override
    public int getItemCount() {
        return payList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userIdRe, amountRe, monthRe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //userIdRe=itemView.findViewById(R.id.userIdText);
            //amountRe=itemView.findViewById(R.id.amountText);
            //monthRe=itemView.findViewById(R.id.monthText);

        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.randomText);
        }

        public TextView getView(){
            return view;
        }
    }
}
