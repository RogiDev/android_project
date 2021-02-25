package com.example.vaadapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vaadapp.Fragments.CreateBuilding;
import com.example.vaadapp.Fragments.LoginFragment;
import com.example.vaadapp.Fragments.MainFragment;
import com.example.vaadapp.Fragments.PaymentsFragment;
import com.example.vaadapp.Models.Building;
import com.example.vaadapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CreateBuilding.createBuildingListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userDb = db.collection("users");
    private CollectionReference managerDb = db.collection("managers");
    private CollectionReference building = db.collection("building");



    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new MainFragment());
        fragmentTransaction.commit();
    }



//    public boolean onPrepareOptionsMenu(@NotNull Menu menu)
//    {
//        String user = mAuth.getCurrentUser().getUid();
//        DocumentReference docRef = dbUser.collection("users").document(user);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
//                        Toast.makeText(MainActivity.this, "DocumentSnapshot data",
//                                Toast.LENGTH_SHORT).show();
//
//                    }
//                    else {
//                        Log.d("TAG", "No such document");
//                        Toast.makeText(MainActivity.this, "No such document",
//                                Toast.LENGTH_SHORT).show();
//                        menu.findItem(R.id.newBuilding).setEnabled(false);
//
//                    }
//                } else {
//                    Log.d("TAG", "get failed with ", task.getException());
//                    Toast.makeText(MainActivity.this, "get failed with ",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return true;

//        menu.findItem(R.id.newBuilding).setEnabled(false);
//        menu.removeGroup(R.id.group_building);
//        menu.setGroupVisible(R.id.group_building, false);
//        return false;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.personal_details){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,new MainFragment());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.payments){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,new PaymentsFragment());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.newBuilding){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,new CreateBuilding());
            fragmentTransaction.commit();
        }
        return true;
    }

    public void onCreateNewBuildingPressed(int numBuilding, int apartment, String street, String entrance) {
        String user = mAuth.getCurrentUser().getUid();

        Building buildingNew = new Building(numBuilding, user, apartment, entrance, street);

        building.add(buildingNew)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("succ", "DocumentSnapshot added with ID: " + documentReference.getId());
                        String _id = documentReference.getId();
                        building.document(documentReference.getId()).update("_id",_id);
                        managerDb.document(user).update("buildingId", _id);
                        Toast.makeText(MainActivity.this, "Authentication Success.",
                                Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(MainActivity.this, MainActivity.class));


                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new MainFragment()).commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("fail", "Error adding document", e);
                    }
                });
    }
}