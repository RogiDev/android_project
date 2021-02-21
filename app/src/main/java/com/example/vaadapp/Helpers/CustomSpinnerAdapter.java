package com.example.vaadapp.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vaadapp.Models.Building;
import com.example.vaadapp.R;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<Building> {
    LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Building> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View rowView = layoutInflater.inflate(R.layout.custom_spinner_view, null,true);
        Building building = getItem(position);
        TextView textView = (TextView)rowView.findViewById(R.id.customSpinnerTextview);
        textView.setText(building.toString());
        return rowView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_spinner_view, parent, false);
        }
        Building building = getItem(position);
        TextView textView = (TextView)convertView .findViewById(R.id.customSpinnerTextview);
        textView.setText(building.toString());
        return convertView;
    }
}
