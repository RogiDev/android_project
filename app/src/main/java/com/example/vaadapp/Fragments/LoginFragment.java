package com.example.vaadapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vaadapp.Activities.AuthActivity;
import com.example.vaadapp.R;


public class LoginFragment extends Fragment {
    private onLoginFragmentBtnSelected listener;
    private Button btnLogin;
    private Button btnRegister;
    private EditText loginEmailInput,loginPasswordInput;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onLoginFragmentBtnSelected) {
            listener = (onLoginFragmentBtnSelected) context;
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginEmailInput = view.findViewById(R.id.loginEmailInput);
        loginPasswordInput = view.findViewById(R.id.loginPasswordInput);
        btnLogin = view.findViewById(R.id.loginBtn);
        btnRegister = view.findViewById(R.id.registerBtn);
        btnLogin.setBackgroundColor(Color.rgb(52,52,52));
        btnRegister.setBackgroundColor(Color.rgb(52,52,52));
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRegisterPressed();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoginPressed(loginEmailInput.getText().toString(),loginPasswordInput.getText().toString());
            }
        });
        return view;
    }

    public interface onLoginFragmentBtnSelected{
        public void onLoginPressed(String email,String password);
        public void onRegisterPressed();
    }
}