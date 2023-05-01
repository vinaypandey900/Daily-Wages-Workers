package com.knit.dailywagesworkers;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class payNow extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private  Object response;
    EditText pstatus, amount;
    TextView msg;
    Button btn;

    private BroadcastReceiver batteryInfoReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
        Checkout.preload(getApplicationContext());

        btn = findViewById(R.id.btnpay);
       pstatus = findViewById(R.id.descrp);
      //  amount = findViewById(R.id.amnt);



        ActionBar actionBar = getSupportActionBar();

        // Customize the ActionBar as needed
        if (actionBar != null) {
            actionBar.setTitle("Pay Now"); // Set the title
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));// Show the back button

        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

        // Register the receiver here
        batteryInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra("level", 0);
                Log.d(TAG, "Battery level: " + level);
            }
        };
        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == android.R.id.home) { // back button clicked
            Intent intent = new Intent(this, inside_customer.class);
            startActivity(intent);
            finish(); // close the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        // Unregister the broadcast receiver
        unregisterReceiver(batteryInfoReceiver);
        super.onDestroy();
    }

    private void startPayment() {

        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_qrpqfFukVtKkjq");

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Empower Illiterate");
            options.put("description", "Reference No. #123456");
          //  options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
          // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "5000");//pass amount in currency subunits
            options.put("Empower Illiterate", "dailywagesworkersproject@gmail.com");
            options.put("prefill.contact","9988776655");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }





    }

    public void onPaymentSuccess(String s) {
      //  Intent intent = new Intent(this, SuccessActivity.class);
        String successMsg = "We will call you sooner for Confirmation\n\nPlease note down your Order id\n\nOrder ID: " + s;
        // Start the SuccessActivity and pass the success message as an extra in the intent
        Intent intent = new Intent(this, SuccessActivity.class);
        intent.putExtra("success_msg", successMsg);
        startActivity(intent);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),inside_customer.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }


    @Override
    public void onPaymentError(int i, String s) {

        msg.setText("Something went wrong");
    }
}