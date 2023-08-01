package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepViewHolder> {
    private List<Sleep> sleepList;

    public SleepAdapter(List<Sleep> sleepList)
    {
        this.sleepList = sleepList;
    }

    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sleep_time_view,
                parent, false);
        return new SleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {
        Sleep sleep = sleepList.get(position);
        holder.bindData(sleep);
    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public void deleteItem(int position) {
        // 从数据源中移除指定位置的数据
        sleepList.remove(position);
        // 通知适配器数据集发生变化
        notifyItemRemoved(position);
    }
}
