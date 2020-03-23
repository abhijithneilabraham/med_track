package com.example.abhijithneilabraham.med_track_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewExisting extends AppCompatActivity {
Button newusr,exusr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_existing);
        newusr=(Button)findViewById(R.id.newusr);
        exusr=(Button)findViewById(R.id.exusr);
        Intent inn=getIntent();
        String idnum=inn.getExtras().getString("idnumber");
        newusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in=new Intent(NewExisting.this,UserActivity.class);
            in.putExtra("idn",idnum);
           startActivity(in);

            }
        });
        exusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(NewExisting.this,supplyactivity.class);
                in2.putExtra("ide",idnum);
                startActivity(in2);

            }
        });
    }
}
