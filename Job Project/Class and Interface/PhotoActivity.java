package com.example.jobproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zolad.zoominimageview.ZoomInImageView;


public class PhotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private ZoomInImageView zoomInImageView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        url = getIntent().getStringExtra("image_url");
        zoomInImageView = (ZoomInImageView) findViewById(R.id.zoomImageView);

        Glide.with(this)
                .load(url)
                .into(zoomInImageView);

    }
}
