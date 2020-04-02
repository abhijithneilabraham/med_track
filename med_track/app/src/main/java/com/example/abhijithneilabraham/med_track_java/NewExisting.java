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
        storevals stval1=new storevals();
        newusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stval1.set_user("newusr");
//                BlankFragment1 blfr1=new BlankFragment1();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("storevals",stval1);

                Intent infr=new Intent(NewExisting.this,supplyactivity.class);
                infr.putExtra("storevals",stval1);
                startActivityForResult(infr,1);

            Intent in=new Intent(NewExisting.this,UserActivity.class);
            in.putExtra("idn",idnum);
           startActivity(in);

            }
        });
        exusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stval1.set_user("extusr");
                Intent infr2=new Intent(NewExisting.this,BlankFragment1.class);
                infr2.putExtra("storevals",stval1);
                startActivityForResult(infr2,1);
                Intent in2=new Intent(NewExisting.this,supplyactivity.class);
                in2.putExtra("ide",idnum);


                startActivity(in2);

            }
        });
    }
}
