package com.knit.dailywagesworkers;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class login extends AppCompatActivity {
     Button btn, agent,connect;
    TextView details;


    RecyclerView recyclerView;
    private TextView tvDate;
    private TextView totalUpdatedJob;



    homeAdapterMainClass mainAdapter;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private TextView totalCustomer;

    private DatabaseReference mDatabaseReference,mDatabaseReference1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("jobs");
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference("customer");



        totalUpdatedJob = findViewById(R.id.total_updated_jobs);
        totalCustomer=findViewById(R.id.customer);
        connect=(Button)findViewById(R.id.connectNow);
        details=findViewById(R.id.arrow);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Customer_login.class);
                startActivity(intent);

            }
        });



        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Customer_login.class);
                startActivity(intent);

            }
        });



        recyclerView=(RecyclerView)findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<homeRvMainModel> options =
                new FirebaseRecyclerOptions.Builder<homeRvMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs").limitToFirst(5), homeRvMainModel.class)
                        .build();

        mainAdapter =new homeAdapterMainClass(getApplicationContext(),options);
        recyclerView.setAdapter(mainAdapter);




        // Set the title to an empty string of an ActionBar
        getSupportActionBar().setTitle(" Welcome Back");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));
        }








        tvDate = findViewById(R.id.tv_date);

        // Get today's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Define date format
        String currentDate = sdf.format(new Date()); // Get current date

        // Set today's date to the TextView
        tvDate.setText("Today's date: " + currentDate);


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long totalUsers = snapshot.getChildrenCount();
                totalUpdatedJob.setText(totalUsers+"+");
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
                totalCustomer.setText( totalUsers1+"+");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });





    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.button){
            Intent intent=new Intent(this, Customer_login.class);
            startActivity(intent);
            finish();
            return true;
        }
        if(id==R.id.button2){
            Intent intent1=new Intent(this, activity_agent_login.class);
            startActivity(intent1);
            finish();
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }






    //search bar on actionbar inside 1st activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem item = menu.findItem(R.id.homeSearch);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search based on query
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // Update search results as user types
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


 //   search with the firebase

//    private void txtSearch(String str)
//    {
//        FirebaseRecyclerOptions<homeRvMainModel> options =
//                new FirebaseRecyclerOptions.Builder<homeRvMainModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs").orderByChild("professiom").startAt(str).endAt(str+"~"),homeRvMainModel.class)
//                        .build();
//
//
//        mainAdapter=new homeAdapterMainClass(options);
//        mainAdapter.startListening();
//        recyclerView.setAdapter(mainAdapter);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }






}






