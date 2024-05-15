package com.example.eksamensprojekt.model;


import java.time.LocalDate;

public class Subproject {
    private String subProjectName;
    private String subProjectDescription;

    private LocalDate subProjectStartDate;
    private LocalDate subProjectEndDate;

    private String subProjectStatus;

    public Subproject(String subProjectName, String subProjectDescription, LocalDate subProjectStartDate, LocalDate subProjectEndDate, String subProjectStatus){
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.subProjectStartDate = subProjectStartDate;
        this.subProjectEndDate = subProjectEndDate;
        this.subProjectStatus = subProjectStatus;

    }

    public Subproject() {

    }

    public String getSubProjectName() {
        return subProjectName;
    }
    public String getSubprojectDescription() {
        return subProjectDescription;
    }

    public LocalDate getSubProjectStartDate() {
        return subProjectStartDate;
    }

    public LocalDate getSubProjectEndDate() {
        return subProjectEndDate;
    }
    public void setSubProjectStartDate(LocalDate subProjectStartDate) {
        this.subProjectStartDate = subProjectStartDate;
    }
    public void setSubProjectEndDate(LocalDate subProjectEndDate) {
        this.subProjectEndDate = subProjectEndDate;
    }

    public String getSubProjectStatus() {
        return subProjectStatus;
    }

    public void setSubProjectStatus(String subProjectStatus) {
        this.subProjectStatus = subProjectStatus;
    }


    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }
    public void setSubProjectDescription(String subProjectDescription){
        this.subProjectDescription = subProjectDescription;
    }


}


