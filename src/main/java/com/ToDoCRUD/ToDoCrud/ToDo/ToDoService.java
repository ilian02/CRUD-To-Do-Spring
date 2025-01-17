package com.ToDoCRUD.ToDoCrud.ToDo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getToDoById(Long id) {
        return toDoRepository.findById(id);
    }

    public ToDo updateToDo(Long id, ToDo toDo) {
        if (toDoRepository.existsById(id)) {
            toDo.setId(id);
            return toDoRepository.save(toDo);
        } else {
            return null;
        }
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }
}

