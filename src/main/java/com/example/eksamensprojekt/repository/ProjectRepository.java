package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProjectRepository {
    @Autowired
    private final JdbcTemplate template;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void createProject(Project project) {
        String sql = "INSERT INTO project (projectName, description, startDate, endDate) VALUES (?, ?, ?, ?)";
        template.update(sql, project.getProjectName(), project.getDescription(), project.getStartDate(), project.getEndDate());
    }

    public void editProject(String name, Project editProject) {
        String editSql = "UPDATE project SET projectName = ?, description = ?, startDate = ?, endDate = ? WHERE projectName = ?";
        template.update(editSql , editProject.getProjectName(), editProject.getDescription(),
                editProject.getStartDate(), editProject.getEndDate(), name);
    }

    public void deleteProject(String projectName) {
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
