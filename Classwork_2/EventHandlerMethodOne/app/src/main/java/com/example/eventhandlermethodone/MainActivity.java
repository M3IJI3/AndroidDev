package com.example.eventhandlermethodone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout constraintLayout;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get everything ready here
        constraintLayout = findViewById(R.id.constraintLayout);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnOne)
        {
            constraintLayout.setBackgroundColor(Color.RED);
            Log.e(TAG, "The color changed to Red!");
        }
        else if(view.getId() == R.id.btnTwo)
        {
            constraintLayout.setBackgroundColor(Color.GREEN);
            Log.e(TAG, "The color changed to Green!");
        }
    }
}