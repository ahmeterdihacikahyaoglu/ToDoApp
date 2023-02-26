package com.yemeksepeti.ToDoApp;

import com.yemeksepeti.ToDoApp.model.ToDoItem;
import com.yemeksepeti.ToDoApp.repository.ToDoItemRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ToDoItemTests {

    @Autowired
    private ToDoItemRepo toDoItemRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveToDoItemTest() throws ParseException {
        String str = "2023-03-01";
        Date date = Date.valueOf(str);
        ToDoItem toDoItem = ToDoItem.builder().title("title test").description("description deneme").duedate(date).completed(false).build();
        toDoItemRepo.save(toDoItem);
        Assertions.assertThat(toDoItem.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void gtByIdToDoItemTest() {
        ToDoItem toDoItem = toDoItemRepo.findById(1L).get();
        Assertions.assertThat(toDoItem.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getAllToDoItemTest() {
        List<ToDoItem> employees = toDoItemRepo.findAll();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateToDoItemTest() {
        ToDoItem toDoItem = toDoItemRepo.findById(1L).get();
        toDoItem.setTitle("update title test");
        toDoItem.setDescription("update description test");
        String str = "2023-03-01";
        Date date = Date.valueOf(str);
        toDoItem.setDuedate(date);
        toDoItem.setCompleted(true);
        ToDoItem toDoItemUpdated = toDoItemRepo.save(toDoItem);
        Assertions.assertThat(toDoItemUpdated.getTitle()).isEqualTo("update title test");
        Assertions.assertThat(toDoItemUpdated.getDescription()).isEqualTo("update description test");
        Assertions.assertThat(toDoItemUpdated.getDuedate()).isEqualTo(date);
        Assertions.assertThat(toDoItemUpdated.isCompleted()).isEqualTo(true);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteByIdToDoItemTest() {
        ToDoItem toDoItem = toDoItemRepo.findById(1L).get();
        toDoItemRepo.deleteById(1L);
        ToDoItem toDoItem1 = null;
        Optional<ToDoItem> optionalToDoItem = toDoItemRepo.findById(1L);
        if (optionalToDoItem.isPresent()) {
            toDoItem1 = optionalToDoItem.get();
        }
        Assertions.assertThat(toDoItem1).isNull();
    }
}
