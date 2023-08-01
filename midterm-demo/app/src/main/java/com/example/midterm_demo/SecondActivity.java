package com.example.midterm_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtResult = findViewById(R.id.txtResult);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String result = bundle.getString("amount");
        txtResult.setText(result);
    }
}