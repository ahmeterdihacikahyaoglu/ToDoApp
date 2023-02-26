package com.yemeksepeti.ToDoApp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TODO")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "due_date")
    private Date duedate;
    @Column(name = "completed")
    private boolean completed;

    public ToDoItem(String title, String description, Date duedate, boolean completed) {
        this.title = title;
        this.description = description;
        this.duedate = duedate;
        this.completed = completed;
    }
}
