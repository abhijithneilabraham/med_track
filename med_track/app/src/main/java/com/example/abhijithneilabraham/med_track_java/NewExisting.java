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
         storevals idnumstore=inn.getParcelableExtra("idnumber");
         String idnum=idnumstore.getidstore();
        //newusr.setText(idnum);
        storevals stval1=new storevals();
        newusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  //              String set_usr="newusr";
//                BlankFragment1 blfr1=new BlankFragment1();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("storevals",stval1);

//                Intent infr=new Intent(NewExisting.this,UserActivity.class);
//
//                startActivityForResult(infr,1);
            stval1.set_user("newusr");
            stval1.set_id(idnum);
            Intent in=new Intent(NewExisting.this,UserActivity.class);
     //       in.putExtra("idn",idnum);
            in.putExtra("userval",stval1);
           startActivity(in);

            }
        });
        exusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String set_usr="extusr";
//                Intent infr2=new Intent(NewExisting.this,UserActivity.class);
//                infr2.putExtra("userval",set_usr);
               // startActivityForResult(infr2,1);
                Intent in2=new Intent(NewExisting.this,supplyactivity.class);
                in2.putExtra("ide",idnum);
                in2.putExtra("userval",set_usr);


                startActivity(in2);

            }
        });
    }
}
