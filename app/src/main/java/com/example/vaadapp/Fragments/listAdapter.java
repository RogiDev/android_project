package com.example.vaadapp.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaadapp.R;

import java.util.Random;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaadapp.R;

import java.util.Random;

public class listAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private Random random;

        public listAdapter(int seed) {
            this.random = new Random(seed);
        }

        @Override
        public int getItemViewType(final int position) {
            return R.layout.item;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.getView().setText("aaa");
            //setText(String.valueOf(random.nextInt()));
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

