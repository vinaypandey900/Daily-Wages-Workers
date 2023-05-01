package com.knit.dailywagesworkers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView name,username,email,phone,address,shop,license;


    FirebaseAuth auth;
    Button agentLogout, editButton;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid=user.getUid();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public settingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingFragment newInstance(String param1, String param2) {
        settingFragment fragment = new settingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // ((AppCompatActivity) getActivity()).getSupportActionBar();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();

        // Customize the ActionBar as needed
        if (actionBar != null) {
            actionBar.setTitle("Agent Profile Section"); // Set the title
           actionBar.setDisplayHomeAsUpEnabled(false); // Show the back button
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2))); // Set the background color
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        //logout
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();



    }



    @Override
    public void onResume() {
        super.onResume();
        fetchAgentDetails(getView());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        editButton = view.findViewById(R.id.editButton12);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), update_agent_profile.class);
                startActivity(intent);
            }
        });

        fetchAgentDetails(view);


        Button agentLogout = view.findViewById(R.id.logout2);









        agentLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), login.class);
                startActivity(intent);

            }
        });

        return view;
    }

    private void fetchAgentDetails(View view) {

        name = view.findViewById(R.id.editText1);
        username = view.findViewById(R.id.editText2);
        email = view.findViewById(R.id.editText3);
        phone = view.findViewById(R.id.editText4);
        address = view.findViewById(R.id.agentAddress);
        shop = view.findViewById(R.id.agentShop);
        license = view.findViewById(R.id.agentLicense);



        email.setText(user.getEmail());

        Query query = FirebaseDatabase.getInstance().getReference("agent").orderByKey().equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name1 = snapshot.child(uid).child("name").getValue().toString();
                    String username1 = snapshot.child(uid).child("username").getValue().toString();
                    String phone1 = snapshot.child(uid).child("phone").getValue().toString();
                    String address1 = snapshot.child(uid).child("address").getValue().toString();
                    String shop1 = snapshot.child(uid).child("shopName").getValue().toString();
                    String license1 = snapshot.child(uid).child("license").getValue().toString();
                    name.setText(name1);
                    username.setText(username1);
                    phone.setText(phone1);
                    address.setText(address1);
                    shop.setText(shop1);
                    license.setText(license1);
                }
                else {
                    Toast.makeText(getActivity(), "User Details Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}