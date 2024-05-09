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


    public void createSubProject(Subproject project) {
        String sql = "INSERT INTO subProject (subProjectName, subProjectDescription, subProjectStartDate, subProjectendDate) VALUES (?, ?, ?, ?)";
        template.update(sql, Subproject.getProjectName(), project.getDescription(), project.getStartDate(), project.getEndDate());
    }

    public void updateSubProject(String name, Project updatedProject) {
        String updateSql = "UPDATE project SET projectName = ?, description = ?, startDate = ?, endDate = ? WHERE projectName = ?";
        template.update(updateSql , updatedProject.getProjectName(), updatedProject.getDescription(),
                updatedProject.getStartDate(), updatedProject.getEndDate(), name);
    }

    public void deleteSubProject(String projectName) {
        String deleteSql = "DELETE FROM project WHERE projectName = ?";
        template.update(deleteSql, projectName);
    }

    public List<Project> findAllProject() {
        String sql = "SELECT project_id, projectName, description, startDate, endDate FROM project";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.query(sql, rowMapper);
    }


    public Project findProjectByName(String name) {
        String sql = "SELECT project_id, projectName, description, startDate, endDate FROM project WHERE projectName = ?";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.queryForObject(sql, rowMapper, name);
    }


}
