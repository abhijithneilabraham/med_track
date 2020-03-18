package com.example.abhijithneilabraham.med_track_java;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
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
import java.util.Calendar;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class UserActivity extends AppCompatActivity  {
    //initialisations

    Button btnLogOut,submit1,dobclick,next;
    FirebaseAuth firebaseAuth;
    private DatePicker dpResult;
    private FirebaseAuth.AuthStateListener authStateListener;
    String age;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Spinner spinner1;
    EditText name,address,hosp;
    TextView namehead,addresshead,genderhead,hosphead,dobhead;
    public String genderdet,dobdet;
    public String namedet,adddet,hospdet;
    public String nameh,addh,hosph,genderh,dobh;
    Calendar calendar=Calendar.getInstance();
    int y=calendar.get(Calendar.YEAR)-18;
    int m=calendar.get(Calendar.MONTH);
    int d=calendar.get(Calendar.DATE);


    //methods
    public void writeNewUser(String pid,String head,String data) {
        database.getReference(uid).child(pid).child(head).setValue(data);
    }
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


    //oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        //edittext values
        name=(EditText)findViewById(R.id.name);
        hosp=(EditText)findViewById(R.id.hosp);
        address=(EditText)findViewById(R.id.address);
        //textviews
        namehead=(TextView)findViewById(R.id.namehead);
        addresshead=(TextView)findViewById(R.id.addresshead);
        hosphead=(TextView)findViewById(R.id.hosphead);
        genderhead=(TextView)findViewById(R.id.genderhead);
        dobhead=(TextView)findViewById(R.id.dobhead);
        //Textview String conversions
        nameh=namehead.getText().toString();
        addh=addresshead.getText().toString();
        genderh=genderhead.getText().toString();
        hosph=hosphead.getText().toString();
        dobh=dobhead.getText().toString();
        //EditText string conversions
        namedet=name.getText().toString();
        hospdet=hosp.getText().toString();
        adddet=address.getText().toString();
        //buttons
        submit1=(Button)findViewById(R.id.submit);
        //dpResult = (DatePicker) findViewById(R.id.dpResult);
        dobclick=(Button)findViewById(R.id.dob);
        next=(Button)findViewById(R.id.next);
        //change the values here to update the changes to database

        dobclick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(UserActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                             dobdet=dayOfMonth+"/"+month+"/"+year;
                        }
                    },y,m,d);
                datePickerDialog.show();
            }
        });
       // String dob=dpResult.getDayOfMonth()+"/"+dpResult.getMonth()+"/"+dpResult.getYear();


        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");
        categories.add("Other");
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
                genderdet=String.valueOf(spinner1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//make change in this array only for any updations in the database

        String[] headers= {nameh,addh,hosph,genderh,dobh};



        submit1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                namedet=name.getText().toString();
                hospdet=hosp.getText().toString();
                adddet=address.getText().toString();
                String[] details= {namedet,adddet,hospdet,genderdet,dobdet};
                    for(int i=0;i<details.length;i++) {
                        writeNewUser(idnum, headers[i], details[i]);

                    }
                    Intent i2=new Intent(UserActivity.this,supplyactivity.class);
                    startActivity(i2);
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


