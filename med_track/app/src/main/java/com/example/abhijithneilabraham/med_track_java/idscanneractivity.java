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


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class idscanneractivity extends AppCompatActivity {
    private Button buttonScan,btnlogout2,pendingst;
    private TextView textViewName, textViewAddress;

    //qr code scanner object
    private IntentIntegrator qrScan;
    public  String idnum="";
    public void postData() {
        // Create a new HttpClient and Post Header
        RequestQueue queue = Volley.newRequestQueue(this);


        final   String url ="https://medtrack1234.pythonanywhere.com/";

        //  final String url = url1.replaceAll("\\s+", "");

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idscanneractivity);

        //View objects
        buttonScan = (Button) findViewById(R.id.qr);
        btnlogout2=(Button)findViewById(R.id.btnLogOut2);
        pendingst=(Button)findViewById(R.id.updatedstock);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
    pendingst.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            postData();
            Intent stv=new Intent(idscanneractivity.this,stockView.class);
            startActivity(stv);
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
            Intent in=new Intent(idscanneractivity.this,NewExisting.class);
                in.putExtra("idnumber",idnum);

                startActivity(in);



            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
