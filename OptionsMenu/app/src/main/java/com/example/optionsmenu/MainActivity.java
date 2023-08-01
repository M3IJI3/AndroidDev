package com.example.optionsmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtViewOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewOne = findViewById(R.id.txtViewOne);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_settings)
        {
            txtViewOne.setText("Settings was selected");
            Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.one) {
            txtViewOne.setText("One was selected");
            Toast.makeText(this, "One", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.two) {
            txtViewOne.setText("Two was selected");
            Toast.makeText(this, "Two", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.discard) {
            txtViewOne.setText("Delete was selected");
            Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.search) {
            txtViewOne.setText("Search was selected");
            Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.ActivityOne) {
            Intent intent = new Intent(this, Activity1.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.ActivityTwo) {
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}