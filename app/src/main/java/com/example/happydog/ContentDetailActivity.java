package com.example.happydog;

// ContentDetailActivity.java


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleView, descriptionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        imageView = findViewById(R.id.detail_image);
        titleView = findViewById(R.id.detail_title);
        descriptionView = findViewById(R.id.detail_description);

        // Get the data from the intent
        int imageResId = getIntent().getIntExtra("imageResId", 0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        // Set the data to the views
        imageView.setImageResource(imageResId);
        titleView.setText(title);
        descriptionView.setText(description);
    }
}
