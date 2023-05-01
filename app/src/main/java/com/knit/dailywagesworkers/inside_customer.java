package com.knit.dailywagesworkers;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class inside_customer extends AppCompatActivity {




    RecyclerView recyclerView;


    insideCustomerAdapterClass mainAdapter;

    FirebaseAuth auth;
    TextView text;

    FirebaseUser user;
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inside_customer);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<homeRvMainModel> options =
                new FirebaseRecyclerOptions.Builder<homeRvMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs"), homeRvMainModel.class)
                        .build();



        mainAdapter =new insideCustomerAdapterClass(this,options);
        recyclerView.setAdapter(mainAdapter);


        ActionBar actionBar = getSupportActionBar();

        // Customize the ActionBar as needed
        if (actionBar != null) {
            actionBar.setTitle("Hello Customer"); // Set the title
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));// Show the back button

        }




    }


    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_customer, menu);

        MenuItem item = menu.findItem(R.id.homeSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);


        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search based on query
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // Update search results as user types
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.logout_menu){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }

        if (id == android.R.id.home) { // back button clicked
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish(); // close the current activity
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<homeRvMainModel> options =
                new FirebaseRecyclerOptions.Builder<homeRvMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs").orderByChild("profession").startAt(str).endAt(str+"~"), homeRvMainModel.class)
                        .build();

        mainAdapter=new insideCustomerAdapterClass(this,options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }


}