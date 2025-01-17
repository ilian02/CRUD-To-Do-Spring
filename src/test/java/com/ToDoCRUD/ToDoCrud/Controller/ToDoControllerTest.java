package com.ToDoCRUD.ToDoCrud.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ToDoCRUD.ToDoCrud.Controllers.ToDoController;
import com.ToDoCRUD.ToDoCrud.ToDo.ToDo;
import com.ToDoCRUD.ToDoCrud.ToDo.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;


    private ToDo toDo;

    @BeforeEach
    public void setup() {
        toDo = new ToDo();
        toDo.setDescription("Test ToDo");
        toDo.setCompleted(false);
    }

    @Test
    public void testCreateToDo() throws Exception {
        when(toDoService.createToDo(any(ToDo.class))).thenReturn(toDo);

        mockMvc.perform(post("/api/todos")
                .contentType("application/json")
                .content("{\"description\":\"Test ToDo\",\"completed\":false}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.description").value("Test ToDo"))
            .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    public void testGetToDoById() throws Exception {
        when(toDoService.getToDoById(anyLong())).thenReturn(Optional.of(toDo));

        mockMvc.perform(get("/api/todos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description").value("Test ToDo"))
            .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    public void testGetToDoByIdNotFound() throws Exception {
        when(toDoService.getToDoById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/todos/999"))
            .andExpect(status().isNotFound());
    }
}
