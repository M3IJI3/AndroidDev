package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        btnSubmit = findViewById(R.id.btnSubmit);

        AppFunctions appFunctions = new AppFunctions();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user's name
                String name = editTextFirstName.getText().toString() + " " + editTextLastName.getText().toString();

                // get user's weight and height
                String weight = editTextWeight.getText().toString();
                String height = editTextHeight.getText().toString();

                if(TextUtils.isEmpty(editTextFirstName.getText().toString()))
                {
                    editTextFirstName.setError("Can not be empty!");
                } else if (TextUtils.isEmpty(editTextLastName.getText().toString())) {
                    editTextLastName.setError("Can not be empty!");
                } else if (TextUtils.isEmpty(height)) {
                    editTextHeight.setError("Can not be empty!");
                } else if (TextUtils.isEmpty(weight)) {
                    editTextWeight.setError("Can not be empty!");
                } else {
                    String BMI = appFunctions.calcBMI(weight, height);
                    //System.out.println(BMI);
                    String BMIResult = appFunctions.getBMIResult(BMI);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("name", name);
                    bundle.putString("weight", weight);
                    bundle.putString("height", height);
                    bundle.putString("BMI", BMI);
                    bundle.putString("BMIResult", BMIResult);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

}