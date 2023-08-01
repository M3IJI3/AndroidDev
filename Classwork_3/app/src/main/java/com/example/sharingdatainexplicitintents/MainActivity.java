package com.example.sharingdatainexplicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnSubmit;
    private TextView editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        editTextName = findViewById(R.id.editTextName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passDataMethodOne();
                passDataMethodTwo();
            }
        });
    }

    private void passDataMethodOne()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", editTextName.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void passDataMethodTwo()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("name", editTextName.getText().toString());
        startActivity(intent);
    }
}