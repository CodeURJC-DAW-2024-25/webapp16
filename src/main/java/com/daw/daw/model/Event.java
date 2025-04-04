package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

/**
 * This file defines the Event entity class, which is mapped to the
 * "event2_table" table in the database.
 * The Event class represents an event with attributes such as id, title, type,
 * description, imageFile, and category.
 * It uses JPA annotations to specify how the class should be persisted in the
 * database.
 * The class includes constructors, getters, and setters for its attributes.
 */

@Entity(name = "event2_table")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Lob
    private Blob imageFile;

    private String title;

    private String type;

    private String category;

    public Event() {

    }

    public Event(String title, String type, String description, java.sql.Blob imageFile, String category) {
        this.title = title;
        this.type = type;
        this.imageFile = imageFile;
        this.description = description;
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
