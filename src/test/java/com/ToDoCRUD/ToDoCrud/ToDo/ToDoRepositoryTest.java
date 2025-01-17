package com.ToDoCRUD.ToDoCrud.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    private ToDo toDo;

    @BeforeEach
    public void setup() {
        toDo = new ToDo();
        toDo.setDescription("Test ToDo");
        toDo.setCompleted(false);
    }

    @Test
    public void testSaveToDo() {
        ToDo savedToDo = toDoRepository.save(toDo);
        assertNotNull(savedToDo);
        assertNotNull(savedToDo.getId());
        assertEquals("Test ToDo", savedToDo.getDescription());
        assertFalse(savedToDo.isCompleted());
    }

    @Test
    public void testFindById() {
        ToDo savedToDo = toDoRepository.save(toDo);

        ToDo foundByIdToDo = toDoRepository.findById(savedToDo.getId()).orElse(null);
        assertNotNull(foundByIdToDo);
        assertEquals(savedToDo.getId(), foundByIdToDo.getId());
    }
}
