package com.knit.dailywagesworkers;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class update_agent_profile extends AppCompatActivity {

    Button update_button;
    EditText name_field, username_field, mobile_field,shop, license,address;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_agent_profile);


        name_field = findViewById(R.id.name_field);
        username_field = findViewById(R.id.username_field);
        mobile_field = findViewById(R.id.mobile_field);
        update_button = findViewById(R.id.update_button);
        address = findViewById(R.id.update_address);
        shop = findViewById(R.id.update_shop);
        license = findViewById(R.id.update_license);

        //Fetch Agent Details
        Query query = FirebaseDatabase.getInstance().getReference("agent").orderByKey().equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child(uid).child("name").getValue().toString();
                    String username = snapshot.child(uid).child("username").getValue().toString();
                    String mobile = snapshot.child(uid).child("phone").getValue().toString();
                    String address1 = snapshot.child(uid).child("address").getValue().toString();
                    String shop1 = snapshot.child(uid).child("shopName").getValue().toString();
                    String license1 = snapshot.child(uid).child("license").getValue().toString();

                    name_field.setText(name);
                    username_field.setText(username);
                    mobile_field.setText(mobile);
                    address.setText(address1);
                    shop.setText(shop1);
                    license.setText(license1);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name_field.getText().toString();
                String usernameStr = username_field.getText().toString();
                String mobileStr = mobile_field.getText().toString();
                String addressStr=address.getText().toString();
                String shopStr=shop.getText().toString();
                String licenseStr=license.getText().toString();


                if(!TextUtils.isEmpty(nameStr) && !TextUtils.isEmpty(usernameStr) && !TextUtils.isEmpty(mobileStr) &&  !TextUtils.isEmpty(addressStr) && !TextUtils.isEmpty(shopStr) && !TextUtils.isEmpty(licenseStr)) {
                    String name = nameStr;
                    String username = usernameStr;
                    String address=addressStr;
                    String shop=shopStr;
                    String license=licenseStr;
                    //double mobile = Double.parseDouble(mobileStr);

                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("agent");
                    firebaseAgentData addAgent = new firebaseAgentData(name, username, user.getEmail(), mobileStr,address,shop,license);
                    reference.child(user.getUid()).setValue(addAgent);
                    Toast.makeText(update_agent_profile.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}