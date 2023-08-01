package com.example.project;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SleepViewHolder extends RecyclerView.ViewHolder {

    TextView txtSleepTime;
    TextView txtSleepDuration;
    Switch switchDelete;

    SleepAdapter sleepAdapter;

    public SleepViewHolder(@NonNull View itemView) {
        super(itemView);
        txtSleepTime = itemView.findViewById(R.id.txtSleepTime);
        txtSleepDuration = itemView.findViewById(R.id.txtSleepTimeInView);
        switchDelete = itemView.findViewById(R.id.switchDelete);
    }

    public void bindData(Sleep sleep) {
        txtSleepTime.setText(sleep.getTime());
        txtSleepDuration.setText(sleep.getDuration());
        switchDelete.setChecked(sleep.isDelete());
    }

}
