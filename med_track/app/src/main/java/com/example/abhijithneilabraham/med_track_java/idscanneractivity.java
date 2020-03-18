package com.example.abhijithneilabraham.med_track_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class idscanneractivity extends AppCompatActivity {
    private Button buttonScan,btnlogout2;
    private TextView textViewName, textViewAddress;

    //qr code scanner object
    private IntentIntegrator qrScan;
    public  String idnum="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idscanneractivity);

        //View objects
        buttonScan = (Button) findViewById(R.id.qr);
        btnlogout2=(Button)findViewById(R.id.btnLogOut2);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
        btnlogout2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              FirebaseAuth.getInstance().signOut();
                                              Intent I = new Intent(idscanneractivity.this, ActivityLogin.class);
                                              startActivity(I);
                                          }
                                      }
        );
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

            idnum=result.getContents();
            Intent in=new Intent(idscanneractivity.this,UserActivity.class);
                in.putExtra("idnumber",idnum);
                startActivity(in);



            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
