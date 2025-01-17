package com.ToDoCRUD.ToDoCrud.ToDo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    private ToDo toDo;

    @BeforeEach
    public void setup() {
        toDo = new ToDo();
        toDo.setDescription("Test ToDo");
        toDo.setCompleted(false);
    }

    @Test
    public void testCreateToDo() {
        when(toDoRepository.save(any(ToDo.class))).thenReturn(toDo);

        ToDo createToDo = toDoService.createToDo(toDo);
        assertNotNull(createToDo);
        assertEquals("Test ToDo", createToDo.getDescription());
        assertFalse(createToDo.isCompleted());
        verify(toDoRepository, times(1)).save(toDo);
    }

    @Test
    public void testGetToDoById() {
        when(toDoRepository.findById(1L)).thenReturn(Optional.of(toDo));
        ToDo returnedToDo = toDoService.getToDoById(1L).get();

        assertNotNull(returnedToDo);
        assertEquals("Test ToDo", returnedToDo.getDescription());
        assertFalse(returnedToDo.isCompleted());
        verify(toDoRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteToDoById() {
        doNothing().when(toDoRepository).deleteById(1L);
        toDoRepository.deleteById(1L);
        verify(toDoRepository, times(1)).deleteById(1L);
    }
}
