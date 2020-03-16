package com.example.abhijithneilabraham.med_track_java;

import android.os.Bundle;

import android.view.View;

import android.content.Intent;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    String age;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public void writeNewUser() {


        database.getReference(uid).setValue("Hello");


    }
Spinner spinner1;
    EditText details;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        writeNewUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
       
       // Intent i2=getIntent();
     //   String username=i2.getExtras().getString("emailid");








        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ActivityLogin.class);
                startActivity(I);

            }
        });

    }
}

