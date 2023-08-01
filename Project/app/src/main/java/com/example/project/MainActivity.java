package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtBMI;
    private TextView txtBMIResult;
    private TextView txtBMIMeter;
    private TextView txtSteps;
    private TextView txtWaterLiter;
    private TextView txtWaterMiliLiter;
    private ImageView imgCalorie;
    private ImageView imgWater;
    private ImageView imgSleep;

    private SensorManager sensorManager;
    private Sensor stepSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtWeight = findViewById(R.id.txtWeight);
        txtHeight = findViewById(R.id.txtHeight);
        txtWaterLiter = findViewById(R.id.txtWaterLiter);
        txtWaterMiliLiter = findViewById(R.id.txtWaterMiliLiter);
        txtBMI = findViewById(R.id.txtBMI);
        txtBMIResult = findViewById(R.id.txtBMIResult);
        txtBMIMeter = findViewById(R.id.txtBMIMeter);
        imgCalorie = findViewById(R.id.imgCalorie);
        imgWater = findViewById(R.id.imgWater);
        imgSleep = findViewById(R.id.imgSleep);

        txtSteps = findViewById(R.id.txtSteps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(stepSensorListener, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

        Intent intent = getIntent();

        if(intent != null)
        {
            Bundle bundle = intent.getExtras();
            if(bundle.containsKey("name"))
            {
                String name = bundle.getString("name");
                String weight = bundle.getString("weight") + " kg";
                String height = bundle.getString("height") + " cm";
                String BMI = bundle.getString("BMI");
                String BMIResult = bundle.getString("BMIResult");

                txtName.setText(name);
                txtWeight.setText(weight);
                txtHeight.setText(height);
                txtBMI.setText(BMI);
                txtBMIResult.setText(BMIResult);
                setTxtMeter(BMIResult);
            }

            if(bundle.containsKey("waterIntake"))
            {
                String waterIntake = bundle.getString("waterIntake");

                txtWaterLiter.setText(Float.parseFloat(waterIntake) / 1000 + " L");
                txtWaterMiliLiter.setText(waterIntake + " ml");
            }
        }

        imgCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalorieCounterActivity.class);
                startActivity(intent);
            }
        });

        imgWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WaterCounterActivity.class);
                startActivity(intent);
            }
        });

        imgSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SleepActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTxtMeter(String BMIResult)
    {
        if(BMIResult.equals("Underweight"))
        {
            txtBMIMeter.setText("less than 18.4");
        }
        else if(BMIResult.equals("Normal"))
        {
            txtBMIMeter.setText("18.5 < BMI < 24.9");
        }
        else if(BMIResult.equals("Overweight"))
        {
            txtBMIMeter.setText("25.0 < BMI < 29.9");
        }
        else
        {
            txtBMIMeter.setText("larger than 30.0");
        }
    }

    private SensorEventListener stepSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                int steps = (int) sensorEvent.values[0];
                txtSteps.setText(String.valueOf(steps));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}