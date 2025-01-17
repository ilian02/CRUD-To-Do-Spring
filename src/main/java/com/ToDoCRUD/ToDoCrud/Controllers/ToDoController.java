package com.ToDoCRUD.ToDoCrud.Controllers;

import com.ToDoCRUD.ToDoCrud.ToDo.ToDo;
import com.ToDoCRUD.ToDoCrud.ToDo.ToDoRepository;
import com.ToDoCRUD.ToDoCrud.ToDo.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        Optional<ToDo> toDo = toDoService.getToDoById(id);
        return toDo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        ToDo created = toDoService.createToDo(toDo);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable Long id, @RequestBody ToDo toUpdate) {
        ToDo updatedToDo = toDoService.updateToDo(id, toUpdate);
        return updatedToDo != null ? ResponseEntity.ok(updatedToDo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        toDoService.deleteToDoById(id);
        return ResponseEntity.noContent().build();
    }
}
