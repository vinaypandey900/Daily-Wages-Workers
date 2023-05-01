package com.knit.dailywagesworkers;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        ActionBar actionBar = getSupportActionBar();

        // Customize the ActionBar as needed
        if (actionBar != null) {
            actionBar.setTitle("Please Wait... Redirect to Home Page"); // Set the title
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));// Show the back button

        }

        // Retrieve the success message from the Intent
        String successMsg = getIntent().getStringExtra("success_msg");

        // Display the success message
        TextView successTextView = findViewById(R.id.success_text_view);
        successTextView.setText(successMsg);

    }
}