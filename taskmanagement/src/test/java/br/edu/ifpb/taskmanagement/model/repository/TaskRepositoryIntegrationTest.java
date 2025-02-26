package br.edu.ifpb.taskmanagement.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.ifpb.taskmanagement.model.entity.TaskEntity;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TaskRepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void givenTask_whenSave_thenPersistInDatabase(){

        TaskEntity task = new TaskEntity(null, "New Task" ,false);

        TaskEntity savedTask = taskRepository.save(task);
        
        assertNotNull(savedTask.getId());
        assertEquals("New Task", savedTask.getTitle());
        assertFalse(savedTask.isCompleted());
    }
    
}
