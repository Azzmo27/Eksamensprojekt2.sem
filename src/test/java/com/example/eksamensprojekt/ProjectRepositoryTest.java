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
    public void testEditProject() {
        int projectId = 1;
        Project editedProject = new Project(1, "UpdatedProject1", "UpdatedDescription1", LocalDate.of(2024, 5, 9), LocalDate.of(2024, 5, 10));

        projectRepository.editProject(projectId, editedProject);

        verify(projectRepository, times(1)).editProject(projectId, editedProject);
    }
}
