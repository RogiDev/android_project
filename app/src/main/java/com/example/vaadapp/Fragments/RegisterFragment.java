package com.example.vaadapp.Fragments;

import android.content.Context;
import android.graphics.Color;
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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.vaadapp.R;

public class RegisterFragment extends Fragment {

    Button singUpBtn;
    EditText email,password,firstName,lastName,identityNum;
    private onRegisterFragmentBtnSelected listener;
    Spinner spinner;
    private static final String[] country = { "Israel0", "Israel1", "Israel2", "Israel3", "Israel4"};

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        email = view.findViewById(R.id.registerEmailInput);
        password = view.findViewById(R.id.registerPassInput);
        firstName = view.findViewById(R.id.firstNameInput);
        lastName = view.findViewById(R.id.lastNameInput);
        identityNum = view.findViewById(R.id.identityNumberInput);
        singUpBtn = view.findViewById(R.id.singUpBtn);
        singUpBtn.setBackgroundColor(Color.rgb(52, 52, 52));

        spinner = view.findViewById(R.id.address_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("country", (String) parent.getItemAtPosition(position));
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