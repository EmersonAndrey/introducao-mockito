package br.edu.ifpb.taskmanagement.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Test
    void givenTaskID_whenFind_thenFindTaskInDatabase(){

        TaskEntity task = new TaskEntity(null, "New Task" ,false);

        TaskEntity savedTask = taskRepository.save(task);
        TaskEntity foundTask = taskRepository.findById(savedTask.getId()).get();
        
        assertNotNull(foundTask);
        assertEquals(savedTask.getId(), foundTask.getId());
    }

    @Test
    void givenTask_whenFindAll_thenFindAllTasksInDatabase(){

        TaskEntity task1 = new TaskEntity(null, "New Task 1" ,false);
        TaskEntity task2 = new TaskEntity(null, "New Task 2" ,false);

        TaskEntity savedTask1 = taskRepository.save(task1);
        TaskEntity savedTask2 = taskRepository.save(task2);
        List<TaskEntity> foundTask = taskRepository.findAll();
        
        assertNotNull(foundTask);
        assertNotNull(foundTask.get(0));
        assertNotNull(foundTask.get(1));
        assertEquals(savedTask1, foundTask.get(0));
        assertEquals(savedTask2, foundTask.get(1));
    }

    @Test
    void givenTask_whenDelete_thenDeleteTaskInDatabase(){

        TaskEntity task = new TaskEntity(null, "New Task" ,false);

        TaskEntity savedTask = taskRepository.save(task);
        taskRepository.deleteById(savedTask.getId());

        assertThrows(NoSuchElementException.class, () -> taskRepository.findById(savedTask.getId()).get());
        
    }
    
}
