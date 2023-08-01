package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
    private List<ToDoExercise> toDoExerciseList;

    public ToDoAdapter(List<ToDoExercise> toDoExerciseList)
    {
        this.toDoExerciseList = toDoExerciseList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_view,
                parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDoExercise toDoExercise = toDoExerciseList.get(position);
        holder.bindData(toDoExercise);
    }

    @Override
    public int getItemCount() {
        return toDoExerciseList.size();
    }
}
