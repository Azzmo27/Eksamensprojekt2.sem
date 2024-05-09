package com.example.eksamensprojekt.repository;


import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SubProjectRepository {
    @Autowired
    private final JdbcTemplate template;

    public SubProjectRepository(JdbcTemplate template) {
        this.template = template;
    }


    public void createSubProject(Subproject subProject) {
        String sql = "INSERT INTO subProject (subProjectName, subProjectDescription, subProjectStartDate, subProjectendDate) VALUES (?, ?, ?, ?)";
        template.update(sql, subProject.getSubProjectName(), subProject.getSubprojectDescription(), subProject.getSubProjectStartDate(), subProject.getSubProjectEndDate(), subProject.getSubProjectStatus());
    }

    public void updateSubProject(String name, Subproject updatedSubProject) {
        String updateSql = "UPDATE project SET subProjectName = ?, subProjectDescription = ?, subProjectStartDate = ?, subProjectendDate = ? WHERE subProjectName = ?";
        template.update(updateSql , updatedSubProject.getSubProjectName(), updatedSubProject.getSubprojectDescription(),
                updatedSubProject.getSubProjectStartDate(), updatedSubProject.getSubProjectEndDate(), name);
    }

    public void deleteSubProject(String SubprojectName) {
        String deleteSql = "DELETE FROM project WHERE projectName = ?";
        template.update(deleteSql, SubprojectName);
    }

    public List<Project> findAllSubProject() {
        String sql = "SELECT subProject_id, subProjectName, subProjectDescription, subProjectStartDate, subProjectendDate FROM subProject";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.query(sql, rowMapper);
    }


    public Project findSubProjectByName(String name) {
        String sql = "SELECT subProject_id, subProjectName, subProjectdescription, subProjectStartDate, subProjectendDate FROM subProjec WHERE subProjectName = ?";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.queryForObject(sql, rowMapper, name);
    }


}
