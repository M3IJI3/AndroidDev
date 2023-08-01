package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    String[] time = { "00:00", "00:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00",
                      "5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                      "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                      "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                      "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" };
    TextView txtStartTime;
    TextView txtEndTime;
    SeekBar seekBarFrom;
    SeekBar seekBarEnd;
    Button btnAddExercise;
    Spinner spinnerExercise;
    CheckBox cbExercise;

    int startProgress;
    int endProgress;
    String exercise;
    List<ToDoExercise> toDoExerciseList;
    ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        txtStartTime = findViewById(R.id.txtStartTime);
        txtEndTime = findViewById(R.id.txtEndTime);
        seekBarFrom = findViewById(R.id.seekBarFrom);
        seekBarEnd = findViewById(R.id.seekBarEnd);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        spinnerExercise = findViewById(R.id.spinnerExercise);

        RecyclerView recyclerView = findViewById(R.id.rvToDoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new Gson();
        toDoExerciseList = new ArrayList<>();


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedList = sharedPreferences.getString("listKey", "");

        Type type = new TypeToken<List<ToDoExercise>>() {}.getType();
        toDoExerciseList = gson.fromJson(savedList, type);

        recyclerView.setVisibility(View.VISIBLE);
        toDoAdapter = new ToDoAdapter(toDoExerciseList);
        recyclerView.setAdapter(toDoAdapter);


        spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exercise = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = txtStartTime.getText().toString() + " - " + txtEndTime.getText().toString();

                if(startProgress >= endProgress){
                    Snackbar.make(view, "The time is not reasonable.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    toDoExerciseList.add(new ToDoExercise(false, time, exercise));
                    recyclerView.setVisibility(View.VISIBLE);
                    toDoAdapter = new ToDoAdapter(toDoExerciseList);
                    recyclerView.setAdapter(toDoAdapter);
                    Snackbar.make(view, exercise + " is added to your plan.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String json = gson.toJson(toDoExerciseList);

                    editor.putString("listKey", json);
                    editor.apply();
                }
            }
        });

        seekBarFrom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                startProgress = progress;
                txtStartTime.setText(time[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarEnd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                endProgress = progress;
                txtEndTime.setText(time[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}