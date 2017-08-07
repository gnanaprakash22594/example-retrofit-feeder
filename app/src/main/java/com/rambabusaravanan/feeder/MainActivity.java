package com.rambabusaravanan.feeder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wait few seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate();
            }
        }, 2000);
    }

    // call next activity
    private void navigate() {
        Intent intent = new Intent(this, FeedListActivity.class);
        startActivity(intent);
        finish();
    }
}
