package com.example.eksamensprojekt.model;

import java.sql.Date;

public class Project {

    private int project_id;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;



    public Project (int project_id, String ProjectName, String description, Date startDate, Date endDate){
        this.project_id = project_id;
        this.projectName = ProjectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

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


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date dateStart) {
        this.startDate = dateStart;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date dateEnd) {
        this.endDate = dateEnd;
    }




}
