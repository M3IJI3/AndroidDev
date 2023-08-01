package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class CalorieCounterActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    Button btnAddCalorieRecord;
    Button btnGoToExercise;
    TextView txtTargetCalorie;
    TextView txtDailyCalorieIntake;

    RecyclerView rvCalorieRecord;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    int targetDailyCalorie;
    int accumulateDailyCalorie;
    float percent;

    String[] array = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        SharedPreferences sharedPreferences = getSharedPreferences("calorie", Context.MODE_PRIVATE);
        String calorieTarget = sharedPreferences.getString("calorieTarget", "0 Cals");
        String intake = sharedPreferences.getString("intake","0 Cals");
        float percentage = sharedPreferences.getFloat("percent", 0);

        // activity
        txtTargetCalorie = findViewById(R.id.txtTargetCalorie);
        txtDailyCalorieIntake = findViewById(R.id.txtDailyCalorieIntake);
        btnAddCalorieRecord = findViewById(R.id.btnAddCalorieRecord);
        btnGoToExercise = findViewById(R.id.btnGoToExercise);

        txtTargetCalorie.setText(calorieTarget);
        txtDailyCalorieIntake.setText(intake);


        // calorie progress bar
        circularProgressBar = findViewById(R.id.cpbCalorie);

        circularProgressBar.setProgressWithAnimation(percentage, (long)1500);

        // recycler view
        rvCalorieRecord = findViewById(R.id.rvCalorieRecord);
        layoutManager = new LinearLayoutManager(this);
        rvCalorieRecord.setLayoutManager(layoutManager);
//        adapter = new CalorieRecordRecyclerAdapter();
        rvCalorieRecord.setAdapter(adapter);

        btnAddCalorieRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalorieDialog calorieDialog = new CalorieDialog( CalorieCounterActivity.this,
                        androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog, new CalorieDialog.PriorityListener() {
                    @Override
                    public void setActivityText(String targetCalorie, String calorieIntake) {
                        //Log.e("test", calorieList.get(0));


                        targetDailyCalorie = Integer.parseInt(targetCalorie);
                        accumulateDailyCalorie += Integer.parseInt(calorieIntake);
                        percent = (float) accumulateDailyCalorie / (float) targetDailyCalorie;

                        // update calorie field
                        txtTargetCalorie.setText(targetDailyCalorie + " Cals");
                        txtDailyCalorieIntake.setText(accumulateDailyCalorie + " Cals");

                        // update progress bar
                        circularProgressBar.setProgressWithAnimation(percent * 100, (long)1500);

                        SharedPreferences sharedPreferences = getSharedPreferences("calorie", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("calorieTarget", txtTargetCalorie.getText().toString());
                        editor.putString("intake", accumulateDailyCalorie + " Cals");
                        editor.putFloat("percent", percent * 100);
                        editor.apply();
                    }
                });
                calorieDialog.show();
            }
        });

        btnGoToExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalorieCounterActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
    }
}