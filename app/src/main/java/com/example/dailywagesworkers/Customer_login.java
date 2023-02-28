package com.example.dailywagesworkers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_login extends AppCompatActivity {
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        btn1=(Button) findViewById(R.id.createAccount);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerSignup();
            }
        });

    }
    public void openCustomerSignup(){
        Intent intent=new Intent(this, customer_signupaccount.class);
        startActivity(intent);
    }

}