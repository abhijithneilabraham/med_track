package com.example.abhijithneilabraham.med_track_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class stockView extends AppCompatActivity {
    TextView namest,addressst,commst,remtime;
    String name,address,comm,remdays;
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String flag,remval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        namest=(TextView)findViewById(R.id.namest);
        addressst=(TextView)findViewById(R.id.addressst);
        commst=(TextView)findViewById(R.id.commst);
        remtime=(TextView)findViewById(R.id.remtime);
       DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child(uid);

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(uid);
//
//        Query query = reference.orderByChild("Flag").equalTo("1");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
//                        String k=dataSnapshot.getKey();
//                        namest.setText("yes");
//                        // do with your result
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    Log.v("",""+ snp.getKey()); //displays the key for the node
                    Log.v("",""+ snp.getValue());
                    //gives the value for given keyname
                    String k=snp.getKey();
                    String v=snp.getValue().toString();

                    DatabaseReference m=dbref.child(k).child("Commodity Names");
                    m.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                            for (DataSnapshot snp2 : dataSnapshot2.getChildren()) {
                                String k2 = snp2.getKey();
                                DatabaseReference m2=dbref.child(k).child("Commodity Names").child(k2).child("Flag");
                                m2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                                        long f=dataSnapshot3.getValue(long.class);
                              flag=""+f;
                            if(flag.equals("1")){

                                String nameval=dataSnapshot.child(k).child("Name").getValue(String.class);
                                String addressval=dataSnapshot.child(k).child("Address").getValue(String.class);
                                long r=dataSnapshot.child(k).child("Commodity Names").child(k2).child("Remaining Days").getValue(long.class);
                                remval=""+r;


                                namest.setText(nameval);
                                addressst.setText(addressval);
                                commst.setText(k2);
                                remtime.setText(remval);

                            }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


//                    addressst.setText(k);
//                    m.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
//                            long f=dataSnapshot3.getValue(long.class);
//                             flag=""+f;
//                            if(flag.equals("1")){
//
//                                String nameval=dataSnapshot.child(k).child("Name").getValue(String.class);
//
//                                namest.setText(nameval);
//                            }
//
//                        }

//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });



//                    if(k.equals("aaa")) {
//                        namest.setText("YES");
//                    }
//                    else if(k.equals("good")){
//                        addressst.setText("YEs");
//                    }
//                    else if(k.equals("Commodity Names")){
//                        commst.setText("pls wait this feature is only getting updated");
//                    }
//
//                    else if(k.equals("Remaining Days")){
//                        remtime.setText(v);
//                    }
                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
