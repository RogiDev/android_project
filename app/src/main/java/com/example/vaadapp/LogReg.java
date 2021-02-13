package com.example.vaadapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogReg extends FragmentActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    LinearLayout LayoutBtn;
    LinearLayout LayoutDetails;
    String rememberMail, rememberPass;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        Button btnReg = (Button) findViewById(R.id.buttonReg);
        Button btnLog = (Button) findViewById(R.id.buttonLog);
        Button btnChooseTenant = (Button) findViewById(R.id.buttonTenant);
        Button btnChooseCommittee = (Button) findViewById(R.id.buttonCommittee);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("mail", MODE_PRIVATE);


        LayoutBtn=findViewById(R.id.LayoutBTN);
        LayoutDetails=findViewById(R.id.LayoutDETAILS);


        btnChooseTenant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                LayoutBtn.setVisibility(View.VISIBLE);
                btnChooseCommittee.setVisibility(View.INVISIBLE);
                LayoutDetails.setVisibility(View.VISIBLE);
            }
        });

        btnChooseCommittee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                LayoutBtn.setVisibility(View.VISIBLE);
                btnChooseTenant.setVisibility(View.INVISIBLE);
                LayoutDetails.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        rememberMail = sharedPreferences.getString("EMAIL", "");
        rememberPass = sharedPreferences.getString("PASSWORD", "");
        email.setText(rememberMail);
        password.setText(rememberPass);

        if ( sharedPreferences.getString("EMAIL" , null ) != null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            String KEY = uid;
            Toast.makeText(LogReg.this, "I remember you: " + KEY + " :)", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LogReg.this, MainActivity.class);
            //putExtra("str","I remember you: "+KEY+" :)");
            startActivity(intent);
        }
    }

    public void funcLogin(View view) {
        EditText emailText = findViewById(R.id.emailText);
        String email = emailText.getText().toString();
        String rememberMail = emailText.getText().toString();
        EditText passText = findViewById(R.id.password);
        String password = passText.getText().toString();
        String rememberPass = passText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", rememberMail);
        editor.putString("PASSWORD", rememberPass);
        editor.apply();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LogReg.this, "login OK", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String KEY = "MainApp to MainActivity";
                            Intent intent = new Intent(LogReg.this, MainActivity.class).putExtra("str",KEY);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogReg.this, "login failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}