package com.yemeksepeti.ToDoApp.repository;

import com.yemeksepeti.ToDoApp.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepo extends JpaRepository<ToDoItem, Long> {
}
