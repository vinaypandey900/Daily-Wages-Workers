package com.example.dailywagesworkers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {
    private Button btn, agent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.button);
        agent = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerLogin();
            }
        });

        //agent
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAgentLogin();
            }
        });

    }

    private void openAgentLogin() {
        Intent intent1=new Intent(this, activity_agent_login.class);
        startActivity(intent1);
    }


    public void openCustomerLogin(){
            Intent intent=new Intent(this, Customer_login.class);
            startActivity(intent);
        }
    }