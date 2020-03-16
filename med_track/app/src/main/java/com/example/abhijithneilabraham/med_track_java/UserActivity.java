package com.example.abhijithneilabraham.med_track_java;

import android.os.Bundle;

import android.view.View;

import android.content.Intent;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    String age;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Spinner spinner1;
    EditText details;

    public void writeNewUser(String head,String data) {
        database.getReference(uid).child(head).setValue(data);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        writeNewUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        details=(EditText)findViewById(R.id.usr);
        List<String> categories = new ArrayList<String>();
        categories.add("Name");
        categories.add("Gender");
        categories.add("Address");
        categories.add("Hospital");
        categories.add("Date of Birth");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);



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
