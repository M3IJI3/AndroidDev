package com.example.project;

public class ToDoExercise {
    private String time;
    private String exercise;
    private boolean isCompleted;

    public ToDoExercise(boolean isCompleted, String time, String exercise)
    {
        this.time = time;
        this.exercise = exercise;
        this.isCompleted = isCompleted;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "ToDoExercise{" +
                "time='" + time + '\'' +
                ", exercise='" + exercise + '\'' +
                '}';
    }
}
