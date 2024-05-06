package com.example.eksamensprojekt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private int project_id;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Subproject> subprojectList;
    private List<Task> taskList;


    public Project (int project_id, String ProjectName, String description, LocalDate startDate,LocalDate endDate){
        this.project_id = project_id;
        this.projectName = ProjectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subprojectList = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    public Project() {

    }

    public int getProject_id() {
        return project_id;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate dateStart) {
        this.startDate = dateStart;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate dateEnd) {
        this.endDate = dateEnd;
    }


    public List<Subproject> getSubprojectList() {
        return subprojectList;
    }

    public void setSubprojectList(List<Subproject> subprojectList) {
        this.subprojectList = subprojectList;
    }


    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


}
