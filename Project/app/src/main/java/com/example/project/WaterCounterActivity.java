package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WaterCounterActivity extends AppCompatActivity {

    private TextView txtTargetWater;
    private TextView txtDailyWaterIntake;
    private EditText editTextTargetWater;
    private EditText editTextIntakeWater;
    private Button btnWaterAddRecord;
    private Button btnBackToMain;
    private CircularProgressBar cpbWater;

    float percentage;
    float target;
    float accumulateIntake;

    String waterIntake = "";
    String waterTarget = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_counter);

        txtTargetWater = findViewById(R.id.txtTargetWater);
        txtDailyWaterIntake = findViewById(R.id.txtDailyWaterIntake);
        editTextTargetWater = findViewById(R.id.editTextTargetWater);
        editTextIntakeWater = findViewById(R.id.editTextIntakeWater);
        btnWaterAddRecord = findViewById(R.id.btnAddWaterRecord);
        btnBackToMain = findViewById(R.id.btnBackToMain);
        cpbWater = findViewById(R.id.cpbWater);

        SharedPreferences sharedPreferences = getSharedPreferences("water", Context.MODE_PRIVATE);
        waterTarget = sharedPreferences.getString("waterTarget", null);
        waterIntake = sharedPreferences.getString("waterIntake", null);

        if(waterTarget != null)
        {
            txtTargetWater.setText(waterTarget + " ml");
            txtDailyWaterIntake.setText(waterIntake + " ml");
            cpbWater.setProgressWithAnimation(Float.parseFloat(waterIntake) / Float.parseFloat(waterTarget) * 100, (long)1500);
        }

        btnWaterAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextTargetWater.getText().toString().equals(""))
                {
                    editTextTargetWater.setError("Cannot be empty!");
                } else if (editTextIntakeWater.getText().toString().equals("")) {
                    editTextIntakeWater.setError("Cannot be empty!");
                } else
                {
                    target = Float.parseFloat(editTextTargetWater.getText().toString());
                    accumulateIntake += Float.parseFloat((editTextIntakeWater.getText().toString()));

                    percentage = accumulateIntake / target;
                    cpbWater.setProgressWithAnimation(percentage * 100, (long)1500);

                    txtTargetWater.setText(editTextTargetWater.getText() + " ml");
                    txtDailyWaterIntake.setText((int) accumulateIntake + " ml");

                    SharedPreferences sharedPreferences = getSharedPreferences("water", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("waterTarget", String.valueOf((int)target));
                    editor.putString("waterIntake", String.valueOf((int)accumulateIntake));
                    editor.apply();
                }
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaterCounterActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("waterIntake", waterIntake);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}