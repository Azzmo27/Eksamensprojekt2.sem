package com.example.eksamensprojekt.model;

import java.time.LocalDate;

public class Subproject {

    private int subProjectId;
    private String subProjectName;
    private String subProjectDescription;
    private LocalDate subProjectStartDate;
    private LocalDate subProjectEndDate;
    private String subProjectStatus;
    private int projectId;

    public Subproject(String subProjectName, String subProjectDescription, LocalDate subProjectStartDate, LocalDate subProjectEndDate, String subProjectStatus, int projectId) {
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.subProjectStartDate = subProjectStartDate;
        this.subProjectEndDate = subProjectEndDate;
        this.subProjectStatus = subProjectStatus;
        this.projectId = projectId;
    }
    public Subproject() {
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public String getSubProjectDescription() {
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

    public void setSubProjectDescription(String subProjectDescription) {
        this.subProjectDescription = subProjectDescription;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    @Override
    public String toString() {
        return "Subproject{" +
                "subProjectId=" + subProjectId +
                ", projectId=" + projectId +
                ", subProjectName='" + subProjectName + '\'' +
                ", subProjectDescription='" + subProjectDescription + '\'' +
                ", subProjectStartDate=" + subProjectStartDate +
                ", subProjectEndDate=" + subProjectEndDate +
                ", subProjectStatus='" + subProjectStatus + '\'' +
                '}';
    }
}
