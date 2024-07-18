package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interest_circle")
public class InterestCircle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    //Constructors
    public InterestCircle() {}

    public InterestCircle(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
