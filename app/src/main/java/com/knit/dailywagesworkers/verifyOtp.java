package com.knit.dailywagesworkers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifyOtp extends AppCompatActivity {

    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    private String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);


        // Set the title to an empty string of an ActionBar
        getSupportActionBar().setTitle(" Phone Verification");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));
        }


        TextView txtMobile=findViewById(R.id.txtMobile);
        txtMobile.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        inputCode1=findViewById(R.id.inputCode1);
        inputCode2=findViewById(R.id.inputCode2);
        inputCode3=findViewById(R.id.inputCode3);
        inputCode4=findViewById(R.id.inputCode4);
        inputCode5=findViewById(R.id.inputCode5);
        inputCode6=findViewById(R.id.inputCode6);

        setUpOtpInputs();

        final ProgressBar progressBar=findViewById(R.id.otpProgress);
        final Button btnVerify=findViewById(R.id.btnVerify);

        verificationId=getIntent().getStringExtra("verificationId");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputCode1.getText().toString().trim().isEmpty()
                        || inputCode2.getText().toString().trim().isEmpty()
                        || inputCode3.getText().toString().trim().isEmpty()
                        || inputCode4.getText().toString().trim().isEmpty()
                        || inputCode5.getText().toString().trim().isEmpty()
                        || inputCode6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(verifyOtp.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code=inputCode1.getText().toString()+
                        inputCode2.getText().toString()+
                        inputCode3.getText().toString()+
                        inputCode4.getText().toString()+
                        inputCode5.getText().toString()+
                        inputCode6.getText().toString();

                if(verificationId !=null){
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnVerify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                        Intent intent=new Intent(getApplicationContext(),inside_agent.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(verifyOtp.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });


    }
    private void setUpOtpInputs(){
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}