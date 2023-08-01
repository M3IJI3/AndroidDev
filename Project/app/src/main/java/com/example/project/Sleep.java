package com.example.project;

public class Sleep {
    private String time;
    private String duration;
    private boolean isDelete;

    public Sleep(){ }
    public Sleep(String time, String duration, boolean isDelete) {
        this.time = time;
        this.duration = duration;
        this.isDelete = isDelete;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
