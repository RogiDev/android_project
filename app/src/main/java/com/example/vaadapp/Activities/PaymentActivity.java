package com.example.vaadapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.vaadapp.Helpers.CustomRecycleAdapter;
import com.example.vaadapp.Models.Apartment;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    ArrayList<Apartment> apartments = new ArrayList<>();
    CustomRecycleAdapter<Apartment> recycleAdapter;
    String buildingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_payments);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        db.collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        buildingId = document.get("buildingId").toString();
                        db.collection("buildings").document(buildingId).collection("apartments").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot document: task.getResult()){
                                        Apartment apartment = document.toObject(Apartment.class);
                                        apartments.add(apartment);
                                        recyclerView = findViewById(R.id.recyclerView);
                                        // Add the following lines to create RecyclerView
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recycleAdapter = new CustomRecycleAdapter<>(getApplicationContext(),apartments);
                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setAdapter(recycleAdapter);
                                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });


    }
}