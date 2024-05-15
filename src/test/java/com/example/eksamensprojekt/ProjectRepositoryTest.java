package com.example.eksamensprojekt;

import com.example.eksamensprojekt.model.Project;
import com.example.eksamensprojekt.repository.ConnectionManager;
import com.example.eksamensprojekt.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @MockBean
    private ConnectionManager connectionManager;

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
    public void testEditProject() {
        String projectName = "Project1";
        Project editedProject = new Project(1, "UpdatedProject1", "UpdatedDescription1", LocalDate.of(2024, 5, 9), LocalDate.of(2024, 5, 10));

        projectRepository.editProject(projectName, editedProject);

        verify(projectRepository, times(1)).editProject(projectName, editedProject);
    }
}
