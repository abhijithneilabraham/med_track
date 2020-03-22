package com.example.abhijithneilabraham.med_track_java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


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
    TextView name1;
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child(uid);

    public void readuser(String h,String uid){
        dbref.child(uid).child(h).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nameval=dataSnapshot.getValue(String.class);
                
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
        supplyactivity usr=(supplyactivity)getActivity();
        Bundle idbun=usr.getid();
        String idval=idbun.getString("id1");

        String[] heads= {"Name","Address","Date Of Birth","Gender","Hospital Details"};
        dbref.child(idval).child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nameval=dataSnapshot.getValue(String.class);
                name1.setText(nameval);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





      //  String idn = getArguments().getString("id");

//    UserActivity usr=new UserActivity();
//    String idn=usr.getidnum();




        // String idnumber=activity.getidnum();


        return rootview;
    }
}
