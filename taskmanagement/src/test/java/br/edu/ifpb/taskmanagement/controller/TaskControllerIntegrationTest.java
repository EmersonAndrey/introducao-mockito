package br.edu.ifpb.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.ifpb.taskmanagement.model.repository.TaskRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) 
public class TaskControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    // @Autowired
    // private ;

    @Autowired
    private TaskRepository TaskRepository;


}
