package com.example.vaadapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaadapp.Models.Apartment;
import com.example.vaadapp.Models.Manager;
import com.example.vaadapp.Models.User;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class MainFragment extends Fragment {
    FirebaseAuth mAuth;
    TextView userText,apartmentText;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userDb = db.collection("users");
    String user;
    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        userText = view.findViewById(R.id.userText);
        apartmentText = view.findViewById(R.id.userApartmentText);
        mAuth = FirebaseAuth.getInstance();
        db.collection("users").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                if(task.isSuccessful() && document.exists()){
                    user = document.toObject(User.class).toString();
                    userText.setText(user.toString());
                    String buidldingId = document.toObject(User.class).getBuildingId();
                    String apartmentId = document.toObject(User.class).getApartmentId();
                    db.collection("building").document(buidldingId).collection("apartments").document(apartmentId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot apartDoc = task.getResult();
                            if(apartDoc.exists()){
                                Apartment apartment = apartDoc.toObject(Apartment.class);
                                apartmentText.setText(apartment.toString());
                            }
                        }
                    });
                }else{
                    db.collection("managers").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot managerDoc = task.getResult();
                            if(managerDoc.exists() && task.isSuccessful()){
                            user = managerDoc.toObject(Manager.class).toString();
                            userText.setText(user.toString());
                            apartmentText.setText("Role: Manager");
                            }
                        }
                    });
                }
            }
        });
        return view;
    }
}