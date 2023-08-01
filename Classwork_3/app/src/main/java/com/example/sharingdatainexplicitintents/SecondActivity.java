package com.example.sharingdatainexplicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView txtView = findViewById(R.id.txtView);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String name = bundle.getString("name");
        txtView.setText("Hello! " + name);

    }
}