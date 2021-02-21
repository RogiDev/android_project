package com.example.vaadapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.vaadapp.Fragments.RegisterFragment;
import com.example.vaadapp.Helpers.CustomSpinnerAdapter;
import com.example.vaadapp.Models.Building;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterActivity extends AppCompatActivity {

    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    private RegisterFragment.onRegisterFragmentBtnSelected listener;
    List<Building> spinnerBuildingList;
    TextView addressText;
    Spinner spinner;
    FirebaseFirestore db;
    CollectionReference builingsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        email =  findViewById(R.id.registerEmailInput);
        password =  findViewById(R.id.registerPassInput);
        firstName =  findViewById(R.id.firstNameInput);
        lastName =  findViewById(R.id.lastNameInput);
        identityNum =  findViewById(R.id.identityNumberInput);
        singUpBtn =  findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));
        addressText =  findViewById(R.id.addressTextView);
        db = FirebaseFirestore.getInstance();
        builingsRef = db.collection("building");
        ArrayList<Building> buildings = new ArrayList<>();
        Spinner spinner = findViewById(R.id.spinner);

        builingsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Building building = document.toObject(Building.class);
                        buildings.add(building);
                    }
                }
            }
        });
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this,R.layout.custom_spinner_view,buildings);
        spinner.setAdapter(customSpinnerAdapter);
    }
}