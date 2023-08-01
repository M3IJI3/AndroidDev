package com.example.classwork_15.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classwork_15.AddNewTask;
import com.example.classwork_15.MainActivity;
import com.example.classwork_15.Model.ToDoModel;
import com.example.classwork_15.R;
import com.example.classwork_15.Utils.DatabaseHelper;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> mList;
    private MainActivity activity;
    private DatabaseHelper myDB;

    public ToDoAdapter(DatabaseHelper myDB, MainActivity activity)
    {
        this.activity = activity;
        this.myDB = myDB;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox myCheckbox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myCheckbox = itemView.findViewById(R.id.mcheckbox);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = mList.get(position);
        holder.myCheckbox.setText(item.getTask());
        holder.myCheckbox.setChecked(toBoolean(item.getStatus()));
        holder.myCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    myDB.updateStatus(item.getId(), 1);
                } else {
                    myDB.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public boolean toBoolean(int num)
    {
        return num != 0;
    }

    public Context getContent(){
        return activity;
    }

    public void setTasks(List<ToDoModel> mList)
    {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position)
    {
        ToDoModel item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position)
    {
        ToDoModel item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



}
