package com.yemeksepeti.ToDoApp.controller;

import com.yemeksepeti.ToDoApp.model.ToDoItem;
import com.yemeksepeti.ToDoApp.repository.ToDoItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api")
public class ToDoItemController {
    @Autowired
    public ToDoItemRepo toDoItemRepo;

    @GetMapping(path = "todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ToDoItem>> getAll() {
        return ResponseEntity.ok(toDoItemRepo.findAll());
    }

    @GetMapping(path = "todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(toDoItemRepo.findById(id));
    }

    @PostMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoItem> save(@RequestBody ToDoItem toDoItem) {
        return ResponseEntity.ok(toDoItemRepo.save(toDoItem));
    }

    @DeleteMapping(path = "todo/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        toDoItemRepo.deleteById(id);
    }

    @PutMapping(path = "todo/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoItem> update(@PathVariable Long id, @RequestBody ToDoItem toDoItem) {
        toDoItem.setId(id);
        return ResponseEntity.ok(toDoItemRepo.save(toDoItem));
    }
}
