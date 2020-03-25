package com.example.abhijithneilabraham.med_track_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stockView extends AppCompatActivity {
    TextView namest,addressst,commst,remtime;
    String name,address,comm,remdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        namest=(TextView)findViewById(R.id.namest);
        addressst=(TextView)findViewById(R.id.addressst);
        commst=(TextView)findViewById(R.id.commst);
        remtime=(TextView)findViewById(R.id.remtime);
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("dgLWfQY9sZMumFUXqnE8tauIigp2");

        dbref.child("5555555").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    Log.v("",""+ snp.getKey()); //displays the key for the node
                    Log.v("",""+ snp.getValue());
                    //gives the value for given keyname
                    String k=snp.getKey();
                    String v=snp.getValue().toString();
                    if(k.equals("Name")) {
                        namest.setText(v);
                    }
                    else if(k.equals("Address")){
                        addressst.setText(v);
                    }
                    else if(k.equals("Commodity Names")){
                        commst.setText("pls wait this feature is only getting updated");
                    }

                    else if(k.equals("Remaining Days")){
                        remtime.setText(v);
                    }
                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
