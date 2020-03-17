package com.example.abhijithneilabraham.med_track_java;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.view.View;

import android.content.Intent;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity  {
    Button btnLogOut,submit1,dob;
    FirebaseAuth firebaseAuth;

    private DatePicker dpResult;

    private FirebaseAuth.AuthStateListener authStateListener;
    String age;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Spinner spinner1;
    EditText details;
   public String headval,dataval;
    public void writeNewUser(String pid,String head,String data) {
        database.getReference(uid).child(pid).child(head).setValue(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        details=(EditText)findViewById(R.id.usr);
        submit1=(Button)findViewById(R.id.submit);
        dpResult = (DatePicker) findViewById(R.id.dpResult);
        String dob=dpResult.getDayOfMonth()+"/"+dpResult.getMonth()+"/"+dpResult.getYear();

        List<String> categories = new ArrayList<String>();
        categories.add("Name");
        categories.add("Address");
        categories.add("Hospital");
        categories.add("Date of Birth");
        Intent inu=getIntent();
        String idnum=inu.getExtras().getString("idnumber");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                headval=String.valueOf(spinner1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        submit1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dataval=details.getText().toString();
                writeNewUser(idnum,headval,dataval);
                writeNewUser(idnum,"Date Of Birth",dob);
            }
        });


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


