package com.example.demo3.entity;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    private long id;
    private String rating;
    private String description;

    //@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, targetEntity = Course.class)
    @ManyToOne()
    //@JoinColumn(name = "custom_name", referencedColumnName = "id")
    private Course course;

    public Review() {
    }

    public long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
