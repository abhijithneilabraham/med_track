package com.example.abhijithneilabraham.med_track_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class supplyactivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private String idn;
    String n,a,h,d,g,c,i;
  //  TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplyactivity);

        dl = (DrawerLayout)findViewById(R.id.activity_supply);
        t = new ActionBarDrawerToggle(this, dl,R.string.common_google_play_services_enable_button, R.string.common_google_play_services_update_button);

        dl.addDrawerListener(t);
        t.syncState();
        Intent in3=getIntent();
        //idn=in3.getExtras().getString("ide");
        //test=(TextView)findViewById(R.id.testpurpose);
        storevals stv=in3.getParcelableExtra("storevalu");
        n=stv.getname();
        a=stv.getaddress();
        h=stv.gethosp();
        d=stv.getdob();
        g=stv.getgender();
        c=stv.getuser();
        i=stv.getidstore();

       // test.setText(stv.getuser());

//        Bundle data=new Bundle();
//        data.putString("id",idn);
//        BlankFragment1 bl1= new BlankFragment1();
//        bl1.setArguments(data);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;

                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(supplyactivity.this, "Details of the Customer",Toast.LENGTH_SHORT).show();


                        fragment=new BlankFragment1();


                        break;


                    case R.id.cat1:
                        Toast.makeText(supplyactivity.this, "Categoty 1 Products",Toast.LENGTH_SHORT).show();

                        fragment=new BlankFragment2();
                        break;
                    case R.id.cat2:
                        Toast.makeText(supplyactivity.this, "Category 2 products",Toast.LENGTH_SHORT).show();
                        fragment=new BlankFragment3();
                        break;
                    case R.id.cat3:
                        Toast.makeText(supplyactivity.this, "Category 3 products",Toast.LENGTH_SHORT).show();
                        fragment=new BlankFragment4();
                        break;

                    default:
                        return true;
                }



                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flContent, fragment);
                    ft.commit();
                }

                return true; }
        });


    }
    public Bundle getid(){
        Bundle hm = new Bundle();
        hm.putString("id1",i);
        hm.putString("namestore",n);
        hm.putString("addressstore",a);
        hm.putString("hospitalstore",h);
        hm.putString("dobstore",d);
        hm.putString("genderstore",g);
        hm.putString("currentuser",c);
        return hm;
    }
//    public Bundle getstoreval(){
//        Bundle st=new Bundle();
//
//
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}