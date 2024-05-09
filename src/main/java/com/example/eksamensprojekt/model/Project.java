package com.example.eksamensprojekt.model;

import java.time.LocalDate;

public class Project {

    private int projectId;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;



    public Project (int projectId, String ProjectName, String description, LocalDate startDate, LocalDate endDate){
        this.projectId = projectId;
        this.projectName = ProjectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

    }
    public Project() {

    }
    public int getProjectId() {
        return projectId;
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




}
