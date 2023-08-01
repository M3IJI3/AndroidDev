package com.example.project;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    CheckBox cbExercise;
    TextView txtExerciseTime;
    TextView txtExercise;

    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
        cbExercise = itemView.findViewById(R.id.cbExercise);
        txtExerciseTime = itemView.findViewById(R.id.txtSleepTime);
        txtExercise = itemView.findViewById(R.id.txtExercise);
    }

    public void bindData(ToDoExercise toDoExercise) {
        cbExercise.setChecked(toDoExercise.isCompleted());
        txtExerciseTime.setText(toDoExercise.getTime());
        txtExercise.setText(toDoExercise.getExercise());
    }
}
