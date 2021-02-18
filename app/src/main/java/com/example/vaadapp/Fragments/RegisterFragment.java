package com.example.vaadapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.vaadapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    private onRegisterFragmentBtnSelected listener;
    Spinner buildingsSpinner;
    Map<String,Object> buildingHash;
    ArrayList<String> spinnerBuildingList;
    TextView addressText;
    public RegisterFragment() {
        // Required empty public constructor
    }


    public void initSpinner(HashMap<String,Object> arr){
        for (Map.Entry<String,Object> obj : arr.entrySet()){
            if(obj.getKey().equals("address")){
                spinnerBuildingList.add((String) obj.getValue());
            }
            Log.d(obj.getKey(), obj.getValue() + "");
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        spinnerBuildingList = new ArrayList<String>();
        email = view.findViewById(R.id.registerEmailInput);
        password = view.findViewById(R.id.registerPassInput);
        firstName = view.findViewById(R.id.firstNameInput);
        lastName = view.findViewById(R.id.lastNameInput);
        identityNum = view.findViewById(R.id.identityNumberInput);
        singUpBtn = view.findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));
        addressText = view.findViewById(R.id.addressTextView);
        buildingsSpinner = view.findViewById(R.id.address_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,spinnerBuildingList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingsSpinner.setAdapter(adapter);
        buildingsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSingUpPressed(email.getText().toString(), password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), identityNum.getText().toString());
            }});
        return view;
    }

    public interface onRegisterFragmentBtnSelected{
        public void onSingUpPressed(String email,String password,String firstName,String lastName,String identity);
    }
}