package com.knit.dailywagesworkers;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class inside_agent extends AppCompatActivity {


    BottomNavigationView bnv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_agent);

        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;

                switch(item.getItemId()){
                    case R.id.agentHome: temp=new HomeFragment();
                    break;

                    case R.id.agentAdd: temp=new addFragment();
                        break;

                    case R.id.setting: temp=new settingFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,temp).commit();

                return true;
            }
        });


    }
}
