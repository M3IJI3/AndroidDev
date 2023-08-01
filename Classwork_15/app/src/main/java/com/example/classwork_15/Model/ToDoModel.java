package com.example.classwork_15.Model;

public class ToDoModel {
    private String task;
    private int id, status;

    public ToDoModel(){ }

    public ToDoModel(String task, int id, int status) {
        this.task = task;
        this.id = id;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
