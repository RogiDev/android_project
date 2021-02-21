package com.example.vaadapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.vaadapp.Models.Building;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterFragment extends Fragment {

    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    private onRegisterFragmentBtnSelected listener;
    List<Building> spinnerBuildingList;
    TextView addressText;
    Spinner spinner;
    FirebaseFirestore db;
    CollectionReference builingsRef;

    public RegisterFragment() {
        // Required empty public constructor
    }
    public void initSpinner(ArrayList<Building> arr){
        spinnerBuildingList.addAll(arr);

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RegisterFragment.onRegisterFragmentBtnSelected) {
            listener = (RegisterFragment.onRegisterFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void updateTextView (String str){
        addressText.setText(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        spinnerBuildingList = new ArrayList<>();;
        email = view.findViewById(R.id.registerEmailInput);
        password = view.findViewById(R.id.registerPassInput);
        firstName = view.findViewById(R.id.firstNameInput);
        lastName = view.findViewById(R.id.lastNameInput);
        identityNum = view.findViewById(R.id.identityNumberInput);
        singUpBtn = view.findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));
        addressText = view.findViewById(R.id.addressTextView);
        selectFromSpinner(view);
        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSingUpPressed(email.getText().toString(), password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), identityNum.getText().toString());
            }});
        return view;
    }

    public interface onRegisterFragmentBtnSelected{
        public void onSingUpPressed(String email,String password,String firstName,String lastName,String identity);
        public void onSelectedItemSpinner(Building building);

    }

    public View selectFromSpinner(View view ){
        db = FirebaseFirestore.getInstance();
        builingsRef = db.collection("building");
        ArrayList<String> buildings = new ArrayList<>();
        Spinner spinner = view.findViewById(R.id.spinner);
        // setSelection = Jump directly to a specific item in the adapter data
        builingsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String building = document.toObject(Building.class).toString();
                        buildings.add(building);
                    }
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, buildings);
        spinner.setGravity(Gravity.CENTER);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }


}