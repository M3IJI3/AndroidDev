package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SleepActivity extends AppCompatActivity {
    String[] sleepGoal ={ "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5","10", "10.5", "11", "11.5", "12" };
    String[] Hour = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13"};
    String[] Min = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    SeekBar seekBarSleepGoal;
    SeekBar seekBarHour;
    SeekBar seekBarMin;
    Switch switchAMPM;
    TextView txtSleepGoal;
    TextView txtAmPm;
    TextView txtSleepClock;
    Button btnConfirm;

    String sleepTime = "6";
    String hour = "00";
    String min = "00";

    String temp = "";

    List<Sleep> sleepList;
    SleepAdapter sleepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        seekBarSleepGoal = findViewById(R.id.seekBarSleepGoal);
        txtSleepGoal = findViewById(R.id.txtSleepGoal);
        seekBarHour = findViewById(R.id.seekBarHour);
        seekBarMin = findViewById(R.id.seekBarMin);
        switchAMPM = findViewById(R.id.switchAmPm);
        btnConfirm = findViewById(R.id.btnSleepConfirm);
        txtAmPm = findViewById(R.id.txtAmPm);
        txtSleepClock = findViewById(R.id.txtSleepClock);
        RecyclerView recyclerView = findViewById(R.id.rvSleepList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sleepList = new ArrayList<>();

        seekBarSleepGoal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int seekBarWidth = seekBar.getWidth();
                int thumbWidth = seekBar.getThumb().getBounds().width();
                int thumbOffset = seekBar.getThumbOffset();
                int xPos = (int) ((seekBarWidth - thumbWidth) * progress / (float) seekBar.getMax())
                        + thumbOffset - txtSleepGoal.getWidth() / 2 + thumbWidth / 2;
                txtSleepGoal.setX(xPos);
                txtSleepGoal.setText("         " + sleepGoal[progress]);
                sleepTime = sleepGoal[progress];
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                hour = Hour[progress];
                txtSleepClock.setText(hour + ":" + min);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                min = Min[progress];
                txtSleepClock.setText(hour + ":" + min);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switchAMPM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    temp = "PM";
                    txtAmPm.setText(temp);
                }
                else
                {
                    temp = "AM";
                    txtAmPm.setText(temp);
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = txtAmPm.getText().toString() + " " + txtSleepClock.getText().toString();

                sleepList.add(new Sleep(s, sleepTime + " hrs", false));
                sleepAdapter = new SleepAdapter(sleepList);
                recyclerView.setAdapter(sleepAdapter);
            }
        });
    }
}