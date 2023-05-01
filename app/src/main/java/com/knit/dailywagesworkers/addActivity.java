package com.knit.dailywagesworkers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addActivity extends AppCompatActivity {

    EditText name, profession,surl,address,phone;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText) findViewById(R.id.popName);
        profession=(EditText) findViewById(R.id.popProfession);
        address=(EditText)findViewById(R.id.popAddress);
        phone=(EditText)findViewById(R.id.popPhone);
        surl=(EditText) findViewById(R.id.popImage);

        btnAdd=(Button) findViewById(R.id.popAdd);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });


    }
    private void insertData(){
        Map<String, Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("profession",profession.getText().toString());
        map.put("address",address.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("surl",surl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("jobs").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addActivity.this, "Worker Data Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addActivity.this, "Error While Adding data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        name.setText("");
        profession.setText("");
        address.setText("");
        phone.setText("");
        surl.setText("");

    }
}