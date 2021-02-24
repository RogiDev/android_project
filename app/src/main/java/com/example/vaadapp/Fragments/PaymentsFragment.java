package com.example.vaadapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaadapp.Models.Apartment;
import com.example.vaadapp.Models.Payments;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PaymentsFragment extends Fragment {

    private RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentActivity listener;
    private FirebaseAuth mAuth;
    ArrayList<Apartment> apartments = new ArrayList<>();
    MyAdapter adapter;



    //private MyAdapter adapter;
    private ArrayList<Payments> paymentList;

    public PaymentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payments, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        recyclerView = view.findViewById(R.id.recyclerView);
        CollectionReference apartmentsRef = db.collection("building").document(uid).collection("apartments");
        // Add the following lines to create RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        apartmentsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("success", document.getId() + " => " + document.getData());
                        Apartment apartment = document.toObject(Apartment.class);
                        apartments.add(apartment);

                    }
                } else {
                    Log.w("lalal", "Error getting documents.", task.getException());
                }
            }
        });
        return view;
    }


}