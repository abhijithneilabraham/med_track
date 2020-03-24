package com.example.abhijithneilabraham.med_track_java;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinnercat1,spinnercat1dur;
    View rootview2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String comm="Commodity Names";
    String Dur="Period";
    String amountdur="Dosage";
    String numstockstr="Stock";
    String cat1val;
    Button addcart;
    String catdur1val;
    String doseval;
    String stockval;
    String idval;

    EditText dose,numStock;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
        // Required empty public constructor
    }
    public void writeNewUser(String pid,String par,String head,String data) {
        database.getReference(uid).child(pid).child(comm).child(par).child(head).setValue(data);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters

    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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
        rootview2=inflater.inflate(R.layout.fragment_blank2, container, false);
        supplyactivity usr=(supplyactivity)getActivity();
        Bundle idbun=usr.getid();
        idval=idbun.getString("id1");
        dose=(EditText)rootview2.findViewById(R.id.numberofdose);
        numStock=(EditText)rootview2.findViewById(R.id.numberofstock);

        String [] values =
                {"Select an option","First","Second","Third"};
        spinnercat1=(Spinner)rootview2.findViewById(R.id.spinnercat1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnercat1.setAdapter(adapter);
        String[] values2={"Day","Week","Month"};
        spinnercat1dur=(Spinner)rootview2.findViewById(R.id.spinnercat1dur);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnercat1dur.setAdapter(adapter2);
        spinnercat1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat1val=String.valueOf(spinnercat1.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnercat1dur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catdur1val=String.valueOf(spinnercat1dur.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



       //int stockvalnum=Integer.parseInt(stockval);
       //int dosevalnum=Integer.parseInt(doseval);
        addcart=(Button)rootview2.findViewById(R.id.submitfr1);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database.getReference(uid).child(idval).child(comm).child(cat1val).child(Dur).child(catdur1val).setValue(dosevalnum);
                //database.getReference(uid).child(idval).child(comm).child(cat1val).child(numstockstr).setValue(stockvalnum);
            buttonClicked(v);
            }
        });

        // Inflate the layout for this fragment
        return rootview2;
    }
    public void buttonClicked (View view) {
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doseval=dose.getText().toString();
                stockval=numStock.getText().toString();
                int dosevalnum=Integer.parseInt(doseval);
                int stockvalnum=Integer.parseInt(stockval);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            database.getReference(uid).child(idval).child(comm).child(cat1val).child(Dur).child(catdur1val).setValue(dosevalnum);
            database.getReference(uid).child(idval).child(comm).child(cat1val).child(numstockstr).setValue(stockvalnum);
            database.getReference(uid).child(idval).child(comm).child(cat1val).child("Date").setValue(date);

            }
        });
    }

}
