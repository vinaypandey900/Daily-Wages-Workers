package com.knit.dailywagesworkers;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    public addFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addFragment newInstance(String param1, String param2) {
        addFragment fragment = new addFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Set the title to an empty string


        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false); // Show the back button
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));
        }

        setHasOptionsMenu(true);


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add, container, false);


        //floating action button for add workers

        FloatingActionButton floatingActionButton=(FloatingActionButton) view.findViewById(R.id.floatingActionButton3);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),addActivity.class));
            }
        });
        
        
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs"), MainModel.class)
                        .build();

        mainAdapter =new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search, menu);

        MenuItem item= menu.findItem(R.id.search);

        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setIconified(true);

        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs").orderByChild("name").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();

        mainAdapter=new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}