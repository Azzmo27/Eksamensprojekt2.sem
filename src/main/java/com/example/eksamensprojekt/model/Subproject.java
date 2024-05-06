package com.example.eksamensprojekt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subproject {
    private String subProjectName;
    private String subProjectDescription;

    private LocalDate subProjectStartDate;
    private LocalDate subProjectEndDate;

    private String subProjectStatus;

    private List<Task> subprojectTask;

    public Subproject(String subProjectName, String subProjectDescription, LocalDate subProjectStartDate, LocalDate subProjectEndDate, String subProjectStatus){
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.subProjectStartDate = subProjectStartDate;
        this.subProjectEndDate = subProjectEndDate;
        this.subProjectStatus = subProjectStatus;
        this.subprojectTask = new ArrayList<>();
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

    public void setSubProjectStartDate(LocalDate subProjectStartDate) {
        this.subProjectStartDate = subProjectStartDate;
    }

    public LocalDate getSubProjectEndDate() {
        return subProjectEndDate;
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

    public List<Task> getSubprojectTask() {
        return subprojectTask;
    }

    public void setSubprojectTask(List<Task> subprojectTask) {
        this.subprojectTask = subprojectTask;
    }

}


