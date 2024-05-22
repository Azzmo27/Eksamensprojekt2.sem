package com.example.eksamensprojekt.model;

import java.time.LocalDate;

public class Project {

    private int projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project (int projectId, String ProjectName, String projectDescription, LocalDate startDate, LocalDate endDate){
        this.projectId = projectId;
        this.projectName = ProjectName;
        this.projectDescription= projectDescription;
        this.startDate = startDate;
        this.endDate = endDate;

    }
    public Project() {

    }
    public int getProjectId() {
        return projectId;
    }


    public void setProjectId(int projectId){
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }


    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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
