package com.example.dailywagesworkers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inside_customer extends AppCompatActivity {

    FirebaseAuth auth;
    TextView text;
    Button btn;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_customer);

        auth=FirebaseAuth.getInstance();
        text=findViewById(R.id.user_details);
        btn=findViewById(R.id.logout);
        user=auth.getCurrentUser();

        if(user==null){
            Intent intent=new Intent(getApplicationContext(), Customer_login.class);
            startActivity(intent);
            finish();
        }
        else{
            text.setText(user.getEmail());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(), Customer_login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}