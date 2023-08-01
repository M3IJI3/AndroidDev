package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class CalorieDialog extends Dialog {
    private Context context;
    private EditText editTextTargetCalorie;
    private EditText editTextCalorieIntake;
    private Button btnUpdateCalorie;


    public interface PriorityListener {
        void setActivityText(String targetCalorie, String calorieIntake);
    }

    private PriorityListener priorityListener;

    public CalorieDialog(Context context, int theme, PriorityListener priorityListener)
    {
        super(context, theme);
        this.context = context;
        this.priorityListener = priorityListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calorie_dialog, null);
        setContentView(view);
        setCancelable(true);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int)(d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);

        editTextTargetCalorie = view.findViewById(R.id.editTextTargetCalorie);
        editTextCalorieIntake = view.findViewById(R.id.editTextCalorieIntake);
        btnUpdateCalorie = view.findViewById(R.id.btnUpdateCalorie);

        btnUpdateCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priorityListener.setActivityText(editTextTargetCalorie.getText().toString(),
                        editTextCalorieIntake.getText().toString());

                dismiss();
            }
        });
    }
}
