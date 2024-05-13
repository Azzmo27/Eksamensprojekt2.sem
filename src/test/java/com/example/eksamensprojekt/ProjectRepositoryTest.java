package com.example.eksamensprojekt;
import java.util.Arrays;
import java.util.List;
import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

public class ProjectRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProjectTest() {
        Project project = new Project();
        project.setProjectName("Test name");
        project.setDescription("Test description");
        project.setStartDate(LocalDate.of(2024, 12, 23));
        project.setEndDate(LocalDate.of(2025, 1, 23));

        assertEquals("Test name", project.getProjectName());
        assertEquals("Test description", project.getDescription());
        assertEquals(LocalDate.of(2024, 12, 23), project.getStartDate());
        assertEquals(LocalDate.of(2025, 1, 23), project.getEndDate());
    }

    @Test
    public void testFindAllProject() {
        List<Project> expectedProjects = Arrays.asList(
                new Project(1, "Project1", "Description1", LocalDate.of(2024, 5, 9), LocalDate.of(2024, 5, 10)),
                new Project(2, "Project2", "Description2", LocalDate.of(2024, 5, 11), LocalDate.of(2024, 5, 12))
        );


    }

    @Test
    public void testEditProject() {

        String projectName = "Project1";
        Project editedProject = new Project(1, "UpdatedProject1", "UpdatedDescription1", LocalDate.of(2024, 5, 9), LocalDate.of(2024, 5, 10));
        String editSql = "UPDATE project SET projectName = ?, description = ?, startDate = ?, endDate = ? WHERE projectName = ?";


        projectRepository.editProject(projectName, editedProject);

        verify(jdbcTemplate, times(1)).update(editSql,
                editedProject.getProjectName(),
                editedProject.getDescription(),
                editedProject.getStartDate(),
                editedProject.getEndDate(),
                projectName);
    }
}








