package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.example.entity.InterestCircle;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String bio;
    private String birthdate;

    @ManyToMany(mappedBy = "artists")
    private List<InterestCircle> interestCircles;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InterestCircle> getCircles() {
        return interestCircles;
    }

    public void setCircles(List<InterestCircle> circles) {
        this.interestCircles = circles;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
