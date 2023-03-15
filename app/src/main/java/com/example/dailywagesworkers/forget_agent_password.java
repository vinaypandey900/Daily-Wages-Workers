package com.example.dailywagesworkers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_agent_password extends AppCompatActivity {

    Button resetPassword;
    EditText emailId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_agent_password);

        resetPassword = findViewById(R.id.btn_reset2);
        emailId = findViewById(R.id.email2);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();


                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forget_agent_password.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(forget_agent_password.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}