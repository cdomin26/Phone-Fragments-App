package com.example.cs478project3_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ExpandImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        ImageView iv = new ImageView(getApplicationContext());

        int picID = i.getIntExtra("3rd", 0);

        iv.setImageResource(MainActivity.phoneImagesArray.get(picID));

        Integer t = picID;
        iv.setTag(t);
        setContentView(iv);

    }
}
