package com.example.eksamensprojekt.model;
import java.time.LocalDate;
import java.sql.Date;

public class Task {
    private String taskName;
    private String taskDescription;
    private Date taskStartDate;
    private Date taskEndDate;
    private int timeEstimate;


    public Task(String taskName, String taskDescription, Date taskStartDate, Date taskEndDate, int timeEstimate){
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

    public Date getTaskStartDate() {
        return taskStartDate;
    }
    public Date getTaskEndDate() {
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

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }
}
