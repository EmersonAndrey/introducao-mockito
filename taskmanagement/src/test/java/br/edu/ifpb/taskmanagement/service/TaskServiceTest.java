package br.edu.ifpb.taskmanagement.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import br.edu.ifpb.taskmanagement.model.entity.TaskEntity;
import br.edu.ifpb.taskmanagement.model.repository.TaskRepository;

@SpringBootTest
public class TaskServiceTest {

    @MockitoBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void givenTaskEntity_whenCreate_thenAddCorrectly() {

        TaskEntity newTask = new TaskEntity();

        when(taskRepository.save(newTask)).thenReturn(newTask);
        taskService.createTask(newTask);

        verify(taskRepository, times(1)).save(newTask);
    }

    @Test
    void givenTaskEntityID_whenFind_thenGetCorrectly() {

        Long taskID = 1L;
        TaskEntity taskEntity = new TaskEntity();

        when(taskRepository.findById(taskID)).thenReturn(Optional.of(taskEntity));
        taskService.getTaskById(taskID);

        verify(taskRepository, times(1)).findById(taskID);
    }

    @Test
    void givenTaskEntity_whenFindAll_thenGetCorrectly() {

        TaskEntity taskEntity = new TaskEntity();
        List<TaskEntity> taskList = new ArrayList<>();
        taskList.add(taskEntity);

        when(taskRepository.findAll()).thenReturn(taskList);
        taskService.getAllTasks();

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void givenTaskEntity_whenUpdate_thenUpdateCorrectly() {

        Long taskID = 1L;
        TaskEntity newTask = new TaskEntity();

        when(taskRepository.findById(taskID)).thenReturn(Optional.of(newTask));
        when(taskRepository.save(newTask)).thenReturn(newTask);
        taskService.updateTask(taskID, newTask);

        verify(taskRepository, times(1)).findById(taskID);
        verify(taskRepository, times(1)).save(newTask);
    }

    @Test
    void givenTaskEntity_whenDelete_thenRemoveCorrectly() {

        Long taskID = 1L;

        when(taskRepository.existsById(taskID)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(taskID);
        taskService.deleteTask(taskID);

        verify(taskRepository, times(1)).deleteById(taskID);;
    }

}
