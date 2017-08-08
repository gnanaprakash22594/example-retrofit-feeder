package com.rambabusaravanan.feeder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        // Check if opened by url click
        if (action.equals(Intent.ACTION_VIEW) && data != null) {
            String[] split = data.toString().split("/");
            String slug = split[6];
            navigateToFeedDetail(slug);
        } else {
            // wait few seconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate();
                }
            }, 2000);
        }

    }

    private void navigateToFeedDetail(String slug) {
        Intent intent = new Intent(this, FeedDetail.class);
        intent.putExtra("slug", slug);
        startActivity(intent);
        finish();
    }

    // call next activity
    private void navigate() {
        Intent intent = new Intent(this, FeedListActivity.class);
        startActivity(intent);
        finish();
    }
}
