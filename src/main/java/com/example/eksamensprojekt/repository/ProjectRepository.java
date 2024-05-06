package com.example.eksamensprojekt.repository;

import com.example.eksamensprojekt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
    @Autowired
    private final JdbcTemplate template;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }
public void createProject(Project project) {
    String sql = "INSERT INTO projects (projectName, description, startDate, endDate) VALUES (?, ?, ?, ?)";
    template.update(sql, project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate());
    }
}

