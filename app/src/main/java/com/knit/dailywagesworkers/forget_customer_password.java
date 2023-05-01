package com.knit.dailywagesworkers;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_customer_password extends AppCompatActivity {
    Button resetPassword;
    EditText emailId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_customer_password);

        resetPassword = findViewById(R.id.btn_reset);
        emailId = findViewById(R.id.email);


        // Set the title to an empty string of an ActionBar
        getSupportActionBar().setTitle("Forget Customer");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));
        }


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();


                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forget_customer_password.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(forget_customer_password.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}