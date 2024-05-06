package com.example.eksamensprojekt.model;
import java.time.LocalDate;

public class Task {
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartDate;
    private LocalDate taskEndDate;
    private int timeEstimate;


    public Task(String taskName, String taskDescription, LocalDate taskStartDate, LocalDate taskEndDate, int timeEstimate){
        this.taskDescription = taskDescription;
        this.taskName = taskName;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.timeEstimate = timeEstimate;

    }
    public String getTaskName() {
        return taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getTaskStartDate() {
        return taskStartDate;
    }
    public LocalDate getTaskEndDate() {
        return taskEndDate;
    }
    public int getTimeEstimate() {
        return timeEstimate;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskEndDate(LocalDate taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public void setTaskStartDate(LocalDate taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }
}
