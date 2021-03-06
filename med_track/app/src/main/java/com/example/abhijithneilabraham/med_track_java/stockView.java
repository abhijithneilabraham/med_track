package com.example.abhijithneilabraham.med_track_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class stockView extends AppCompatActivity {
    //TextView namest,addressst,commst,remtime;
    String name,address,comm,remdays;
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String flag,remval;
    androidx.constraintlayout.widget.ConstraintLayout conlay;
    private ListView dataListView;
    int[] colors = new int[2];
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String nameval,addressval;
    ArrayList<String> details = new ArrayList<String>();

    int fl=0;
    private ProgressBar spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        dataListView = (ListView) findViewById(R.id.listview);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        Toast.makeText(this, "Please wait,data loading..", Toast.LENGTH_LONG).show();



       DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child(uid);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, details){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){

                        // Get the current item from ListView
                        View view = super.getView(position,convertView,parent);
                        if(position%8>=4)
                        {
                            // Set a background color for ListView regular row/item
                            view.setBackgroundColor(Color.parseColor("#FFB6B546"));

                        }
                        else
                        {
                            // Set the background color for alternate row/item
                            view.setBackgroundColor(Color.parseColor("#00FFFF"));

                        }
                        return view;
                    }
                };

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                spinner.setVisibility(View.GONE);
                for (DataSnapshot snp : dataSnapshot.getChildren()) {



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

                                 nameval=dataSnapshot.child(k).child("Name").getValue(String.class);
                                 addressval=dataSnapshot.child(k).child("Address").getValue(String.class);
                                long r=dataSnapshot.child(k).child("Commodity Names").child(k2).child("Remaining Days").getValue(long.class);
                                remval=""+r;


                                 details.add("Name:"+nameval);
                                 details.add("Address:"+addressval);
                                 details.add("Commodity Name:"+k2);
                                 details.add("Remaining Days:"+remval);


                                dataListView.setAdapter(itemsAdapter);






//                                TextView   namest=(TextView) findViewById(R.id.namest);
//                                TextView addressst=(TextView)findViewById(R.id.addressst);
//                                TextView commst=(TextView)findViewById(R.id.commst);
//                                TextView remtime=(TextView)findViewById(R.id.remtime);
//
//                                namest.setText(nameval);
//                                addressst.setText(addressval);
//                                commst.setText(k2);
//                                remtime.setText(remval);


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

                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
