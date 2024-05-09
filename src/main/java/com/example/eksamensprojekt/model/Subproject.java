package com.example.eksamensprojekt.model;


import java.time.LocalDate;

public class Subproject {
    private String subProjectName;
    private String subProjectDescription;

    private Date subProjectStartDate;
    private Date subProjectEndDate;

    private String subProjectStatus;

    public Subproject(String subProjectName, String subProjectDescription, Date subProjectStartDate, Date subProjectEndDate, String subProjectStatus){
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.subProjectStartDate = subProjectStartDate;
        this.subProjectEndDate = subProjectEndDate;
        this.subProjectStatus = subProjectStatus;

    }
    public String getSubProjectName() {
        return subProjectName;
    }
    public String getSubprojectDescription() {
        return subProjectDescription;
    }

    public Date getSubProjectStartDate() {
        return subProjectStartDate;
    }

    public void setSubProjectStartDate(Date subProjectStartDate) {
        this.subProjectStartDate = subProjectStartDate;
    }

    public Date getSubProjectEndDate() {
        return subProjectEndDate;
    }

    public void setSubProjectEndDate(Date ubProjectEndDate) {
        this.subProjectEndDate = subProjectEndDate;
    }

    public String getSubProjectStatus() {
        return subProjectStatus;
    }

    public void setSubProjectStatus(String subProjectStatus) {
        this.subProjectStatus = subProjectStatus;
    }


}


