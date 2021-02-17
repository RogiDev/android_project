package com.example.vaadapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vaadapp.Activities.AuthActivity;
import com.example.vaadapp.R;


public class RegisterFragment extends Fragment {
    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    private onRegisterFragmentBtnSelected listener;

    public RegisterFragment() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        email = view.findViewById(R.id.registerEmailInput);
        password = view.findViewById(R.id.registerPassInput);
        firstName = view.findViewById(R.id.firstNameInput);
        lastName = view.findViewById(R.id.lastNameInput);
        identityNum = view.findViewById(R.id.identityNumberInput);
        Spinner dropdown = view.findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        singUpBtn = view.findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52,52,52));
        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSingUpPressed(email.getText().toString(),password.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),identityNum.getText().toString());
            }
        });
        return view;
    }

    public interface onRegisterFragmentBtnSelected{
        public void onSingUpPressed(String email,String password,String firstName,String lastName,String identity);
    }
}