package com.zoe.zoeng.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Start time is required")
    private LocalDateTime start;

    @NotNull(message = "End time is required")
    private LocalDateTime finish;

    private boolean completed;

    private boolean allDay;

    public Event() {
    }

    public Event(int id, String title, String description, LocalDateTime start, LocalDateTime finish, boolean completed, boolean allDay) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.completed = completed;
        this.allDay = allDay;
    }

    public boolean isAllDay() {return allDay; }

    public void setAllDay(boolean allDay) {this.allDay = allDay; }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
