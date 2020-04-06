package com.example.abhijithneilabraham.med_track_java.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.abhijithneilabraham.med_track_java.ActivityLogin;
import com.example.abhijithneilabraham.med_track_java.NewExisting;
import com.example.abhijithneilabraham.med_track_java.R;
import com.example.abhijithneilabraham.med_track_java.idscanneractivity;
import com.example.abhijithneilabraham.med_track_java.stockView;
import com.example.abhijithneilabraham.med_track_java.storevals;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeFragment extends Fragment {
    private Button btnlogout2,enterid,submit2;
    private TextView textViewName, textViewAddress;
    EditText idedit;

    private IntentIntegrator qrScan;
    protected   String idnum="";
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

         enterid=(Button) root.findViewById(R.id.enteridfr);

        btnlogout2=(Button)root.findViewById(R.id.btnLogOut2fr);


        idedit=(EditText)root.findViewById(R.id.idedittextfr);


        enterid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
                builderSingle.setIcon(R.drawable.ic_launcher_background);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Enter ID Manually");
                arrayAdapter.add("Scan ID QR code");
                String x="Enter ID Manually";


                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                        if (strName.equals(x)){
                        builderInner.setMessage(strName+" In the text field below");
                        }
                        else{
                            builderInner.setMessage("Scan the QR code containing the patient ID tag");

                        }

                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                if(strName.equals("Enter ID Manually"))
                                {
                                    if(idedit.getVisibility()==v.GONE){


                                        idedit.setVisibility(v.VISIBLE);
                                    }

                                }
                                else{
                                    qrScan =  IntentIntegrator.forSupportFragment(HomeFragment.this);
                                    qrScan.initiateScan();
                                }
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();



            }
        });


        //intializing scan object


        //attaching onclick listener

        submit2=(Button)root.findViewById(R.id.submit2fr);
        submit2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v2) {

                                           storevals s=new storevals();
                                           if(idnum.length()==0){
                                               idnum=idedit.getText().toString();

                                           }

                                           s.set_id(idnum);
                                           Intent in=new Intent(getContext(), NewExisting.class);
                                           in.putExtra("idnumber",s);


                                           startActivity(in);
                                       }
                                   }
        );
        btnlogout2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              FirebaseAuth.getInstance().signOut();
                                              Intent I = new Intent(getContext(), ActivityLogin.class);
                                              startActivity(I);
                                          }
                                      }
        );

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                idnum=result.getContents();




            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
