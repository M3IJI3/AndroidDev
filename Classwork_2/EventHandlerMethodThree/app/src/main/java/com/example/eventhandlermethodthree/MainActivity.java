package com.example.eventhandlermethodthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private  ConstraintLayout constraintLayout;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
    }

    public void ChangedToRed(View view) {
        constraintLayout.setBackgroundColor(Color.RED);
        Log.e(TAG, "Changed to red.");
    }

    public void ChangedToGreen(View view) {
        constraintLayout.setBackgroundColor(Color.GREEN);
        Log.e(TAG, "Changed to green.");
    }
}