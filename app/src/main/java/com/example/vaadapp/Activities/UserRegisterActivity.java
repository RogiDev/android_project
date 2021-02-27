package com.example.vaadapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class UserRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button singUpBtn;
    EditText email, password, firstName, lastName, identityNum;
    Spinner buildingSpinner, apartmnetSpinner;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ;
    CollectionReference builingsRef = db.collection("building");
    ;
    ArrayList<Building> buildings = new ArrayList<>();
    ;
    CustomSpinnerAdapter buildingSpinnerAdapter, apartmentSpinnerAdapter;
    int choosenApartment;
    String choosenBuildingId;
    ArrayList<Integer> apartments = new ArrayList<>();
     FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        mAuth = FirebaseAuth.getInstance();
        builingsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            Building building = document.toObject(Building.class);
                            Log.d("items", "onComplete: " + building.toString());
                            buildings.add(building);
                        }
                    }
                    buildingSpinnerAdapter.notifyDataSetChanged();
                }
            }
        });
        email = findViewById(R.id.registerEmailInput);
        password = findViewById(R.id.registerPassInput);
        firstName = findViewById(R.id.firstNameInput);
        lastName = findViewById(R.id.lastNameInput);
        identityNum = findViewById(R.id.identityNumberInput);
        singUpBtn = findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));
        buildingSpinner = findViewById(R.id.myCustomSpinner);
        buildingSpinner.setOnItemSelectedListener(this);
        buildingSpinnerAdapter = new CustomSpinnerAdapter<>(this, R.layout.custom_spinner_view, buildings);
        buildingSpinner.setAdapter(buildingSpinnerAdapter);
        apartmnetSpinner = findViewById(R.id.apartmentSpinner);
        apartmnetSpinner.setOnItemSelectedListener(this);
        apartmentSpinnerAdapter = new CustomSpinnerAdapter<>(this, R.layout.custom_spinner_view, apartments);
        apartmnetSpinner.setAdapter(apartmentSpinnerAdapter);
        singUpBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        CollectionReference apartment = builingsRef.document(buildings.get(position).get_id()).collection("apartments");
        if (parent.getAdapter().equals(buildingSpinnerAdapter)) {
            choosenBuildingId = buildings.get(position).get_id();
            apartments.clear();
            for (int i = 1; i <= buildings.get(position).getMaxApartments(); i++) {
                apartments.add(i);
            }
            apartmentSpinnerAdapter.notifyDataSetChanged();
        }
        if (parent.getAdapter().equals(apartmentSpinnerAdapter)) {
            choosenApartment = apartments.get(position);
            apartments.add(choosenApartment);
            Set<Integer> listWithoutDuplicates = new LinkedHashSet<Integer>(apartments);
            apartments.clear();
            apartments.addAll(listWithoutDuplicates);
            apartmentSpinnerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        // check if apartment is exist
        Query query = db.collection("building")
                .document(choosenBuildingId).collection("apartments")
                .whereEqualTo("apartmentNumber", choosenApartment);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            Toast.makeText(UserRegisterActivity.this, "Sorry The Apartment is already registered", Toast.LENGTH_LONG).show();
                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            Apartment apartmentToSave = new Apartment(choosenApartment);
                            db.collection("building")
                                    .document(choosenBuildingId).collection("apartments").add(apartmentToSave).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        DocumentReference documentReference = task.getResult();
                                        String apartId = documentReference.getId();
                                        documentReference.update("_id", apartId);
                                        // Write a message to the database.0
                                        User newUser = new User(firstName.getText().toString(), lastName.getText().toString(),
                                                uid, email.getText().toString(), apartId, choosenBuildingId);
                                        db.collection("users").document(uid).set(newUser)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        startActivity(new Intent(UserRegisterActivity.this, AuthActivity.class));
                                                        Toast.makeText(UserRegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }
                            });
                        }
                    }

                } else {
                    Log.d("llsl", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}