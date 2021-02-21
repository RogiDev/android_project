package com.example.vaadapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class CreateBuilding extends Fragment {

    Button btnSaveBuilding;
    EditText numBuilding, numMaxApartment, street, entrance;
    TextView thisUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference building = db.collection("building");



    public CreateBuilding() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_building, container, false);

        thisUser = view.findViewById(R.id.currentUser);
        btnSaveBuilding = view.findViewById(R.id.saveBuilding);
        numBuilding = view.findViewById(R.id.buildingNum);
        numMaxApartment = view.findViewById(R.id.MAXapartmentNumber);
        street = view.findViewById(R.id.address);
        entrance = view.findViewById(R.id.enetery);

        return view;
    }

    public void newBuilding(View view){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference("building").child(uid);
            //CollectionReference building = db.collection("building");

            // Create a new user with a first and last name
            Map<String, Object> building = new HashMap<>();
            //building.put("_id", x);
            building.put("address", "Hanegev");
            building.put("buildingNumber", 13);
            building.put("enetery", "b");
            building.put("manager", "/managers/" + uid);
            building.put("maxApartements", 10);

            // Add a new document with a generated ID
            db.collection("building")
                    .add(building)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG2", "Error adding document", e);
                        }
                    });
    }
}


