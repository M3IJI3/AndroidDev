package com.example.eventhandlermethodtwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);

        btnOne.setOnClickListener(view -> {
            constraintLayout.setBackgroundColor(Color.RED);
            Log.e(TAG, "The color set to red.");
        });

        btnTwo.setOnClickListener(view -> {
            constraintLayout.setBackgroundColor(Color.GREEN);
            Log.e(TAG, "The color set to green.");
        });
    }
}