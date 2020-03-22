package com.example.abhijithneilabraham.med_track_java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "param2";
    private DatabaseReference mDatabase;
    View rootview;
    TextView name1,gender1,address1,dob1,hosp1;
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String val;
    String[] valarray=new String[5];
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child(uid);
    HashMap<String,String> hm =new HashMap<String, String>();

    public void readuser(String h,String pid){
        dbref.child(pid).child(h).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 val=dataSnapshot.getValue(String.class);
               hm.put(h,val);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public BlankFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment1.
     */
//    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(String param1, String param2) {
        BlankFragment1 fragment = new BlankFragment1();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview =inflater.inflate(R.layout.fragment_blank1,container,false);
        name1=(TextView)rootview.findViewById(R.id.name1);
        gender1=(TextView)rootview.findViewById(R.id.gender1);
        address1=(TextView)rootview.findViewById(R.id.address1);
        hosp1=(TextView)rootview.findViewById(R.id.hosp1);
        dob1=(TextView)rootview.findViewById(R.id.dob1);



        supplyactivity usr=(supplyactivity)getActivity();
        Bundle idbun=usr.getid();
        String idval=idbun.getString("id1");

        String[] heads= {"Name","Gender","Hospital Details","Address","Date Of Birth"};
        dbref.child(idval).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    Log.v("",""+ snp.getKey()); //displays the key for the node
                    Log.v("",""+ snp.getValue());
                    //gives the value for given keyname
                    String k=snp.getKey();
                    String v=snp.getValue().toString();
                    if(k.equals("Name")) {
                        name1.setText(v);
                    }
                    else if(k.equals("Gender")){
                        gender1.setText(v);
                    }
                    else if(k.equals("Hospital Details")){
                        hosp1.setText(v);
                    }
                    else if(k.equals("Address")){
                        address1.setText(v);
                    }
                    else if(k.equals("Date Of Birth")){
                        dob1.setText(v);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        for(int i=0;i<5;i++){
//          readuser(heads[i],idval);
//
//        }
//        for(int k=0;k<5;k++){
//            String setval=hm.get(heads[k]);
//            if(k==0){
//                name1.setText(setval);
//            }
//            else if(k==1){
//                gender1.setText(setval);
//            }
//            else if(k==2){
//                hosp1.setText(setval);
//            }
//            else if(k==3){
//                address1.setText(setval);
//            }
//            else if(k==4){
//                dob1.setText(setval);
//            }
//        }






      //  String idn = getArguments().getString("id");

//    UserActivity usr=new UserActivity();
//    String idn=usr.getidnum();




        // String idnumber=activity.getidnum();


        return rootview;
    }
}
