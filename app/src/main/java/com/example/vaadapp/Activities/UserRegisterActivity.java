package com.example.vaadapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaadapp.Helpers.CustomSpinnerAdapter;
import com.example.vaadapp.Models.Apartment;
import com.example.vaadapp.Models.Building;
import com.example.vaadapp.Models.User;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class UserRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    Spinner buildingSpinner,apartmnetSpinner;
    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    CollectionReference builingsRef = db.collection("building");;
    ArrayList<Building> buildings =  new ArrayList<>();;
    CustomSpinnerAdapter buildingSpinnerAdapter,apartmentSpinnerAdapter;

    ArrayList<Integer> apartments = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        builingsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.exists()){
                        Building building = document.toObject(Building.class);
                        Log.d("items", "onComplete: "+ building.toString());
                        buildings.add(building);
                        }
                    }
                    buildingSpinner = findViewById(R.id.myCustomSpinner);
                    buildingSpinner.setOnItemSelectedListener(UserRegisterActivity.this);
                    buildingSpinnerAdapter = new CustomSpinnerAdapter(UserRegisterActivity.this,R.layout.custom_spinner_view,buildings);
                    buildingSpinner.setAdapter(buildingSpinnerAdapter);
                }
            }
        });
        email =  findViewById(R.id.registerEmailInput);
        password =  findViewById(R.id.registerPassInput);
        firstName =  findViewById(R.id.firstNameInput);
        lastName =  findViewById(R.id.lastNameInput);
        identityNum =  findViewById(R.id.identityNumberInput);
        singUpBtn =  findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));
        buildingSpinner = findViewById(R.id.myCustomSpinner);
        buildingSpinner.setOnItemSelectedListener(this);
        buildingSpinnerAdapter = new CustomSpinnerAdapter<>(this,R.layout.custom_spinner_view,buildings);
        buildingSpinner.setAdapter(buildingSpinnerAdapter);
        apartmnetSpinner = findViewById(R.id.apartmentSpinner);
        apartmnetSpinner.setOnItemSelectedListener(this);
        apartmentSpinnerAdapter = new CustomSpinnerAdapter<>(this,R.layout.custom_spinner_view,apartments);
        apartmnetSpinner.setAdapter(apartmentSpinnerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        CollectionReference apartment = builingsRef.document(buildings.get(position).get_id()).collection("apartments");
                    if(parent.getAdapter().equals(buildingSpinnerAdapter)) {
                        apartments.clear();
                        for (int i = 1; i <= buildings.get(position).getMaxApartements(); i++) {
                            apartments.add(i);
                        }
                        apartmnetSpinner = findViewById(R.id.apartmentSpinner);
                        apartmentSpinnerAdapter = new CustomSpinnerAdapter<>(UserRegisterActivity.this, R.layout.custom_spinner_view, apartments);
                        apartmnetSpinner.setAdapter(apartmentSpinnerAdapter);

                    }
                    if(parent.getAdapter().equals(apartmentSpinnerAdapter)){
                        apartmnetSpinner = findViewById(R.id.apartmentSpinner);
                        apartmentSpinnerAdapter = new CustomSpinnerAdapter<>(UserRegisterActivity.this, R.layout.custom_spinner_view, apartments);
                        apartmnetSpinner.setAdapter(apartmentSpinnerAdapter);
                    }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}