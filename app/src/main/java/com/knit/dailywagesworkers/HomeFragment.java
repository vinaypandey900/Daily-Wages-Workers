package com.knit.dailywagesworkers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    private DatabaseReference mDatabaseReference,mDatabaseReference1;
    private TextView tvDate;
    private TextView totalCustomer,addJob,deleteJob;
    private TextView totalAgent;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;




    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
       // ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

   //     ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Customize the ActionBar as needed
        if (actionBar != null) {
            actionBar.setTitle("Hello Customer"); // Set the title
            actionBar.setDisplayHomeAsUpEnabled(true); // Show the back button
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2))); // Set the background color
        }

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the LoginActivity when the back button is clicked
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        // Initialize views using findViewById on the inflated view
        totalCustomer= view.findViewById(R.id.customer_total);
        totalAgent = view.findViewById(R.id.agent_total);
        tvDate = view.findViewById(R.id.tv_date);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("agent");
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference("customer");
        addJob=view.findViewById(R.id.addJobs);
        deleteJob=view.findViewById(R.id.dltJob);



        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the activity
                Intent intent = new Intent(getActivity(), addActivity.class);

                // Start the activity
                startActivity(intent);
            }
        });





        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Define date format
        String currentDate = sdf.format(new Date()); // Get current date

        // Set today's date to the TextView
        tvDate.setText("Today's date: " + currentDate);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long totalUsers = snapshot.getChildrenCount();
                totalCustomer.setText(totalUsers+"+");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });



        mDatabaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long totalUsers1 = snapshot.getChildrenCount();
                totalAgent.setText( totalUsers1+"+");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}