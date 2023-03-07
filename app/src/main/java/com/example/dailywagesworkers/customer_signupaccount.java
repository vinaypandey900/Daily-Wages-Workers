package com.example.dailywagesworkers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class customer_signupaccount extends AppCompatActivity {
    EditText fname, userName, emailId, phone,pass;
    Button signUp, alreadyHave;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), inside_customer.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signupaccount);
        mAuth=FirebaseAuth.getInstance();
        fname=findViewById(R.id.editTextTextPersonName);
        userName=findViewById(R.id.editTextTextPersonName2);
        emailId=findViewById(R.id.editTextTextPersonName3);
        phone=findViewById(R.id.editTextPhone);
        signUp=findViewById(R.id.button3);
        alreadyHave=findViewById(R.id.alreadyHave);
        pass=findViewById(R.id.textInputLayout);



        alreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Customer_login.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email=String.valueOf(emailId.getText());
                password=String.valueOf(pass.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(customer_signupaccount.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(customer_signupaccount.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(customer_signupaccount.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(customer_signupaccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}