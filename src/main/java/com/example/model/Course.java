package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    private int crn;
    private String courseName;
    private String courseTiming;
    private String courseInstructor;

    public Course() {}

    public Course(int crn, String courseName, String courseTiming, String courseInstructor) {
        this.crn = crn;
        this.courseName = courseName;
        this.courseTiming = courseTiming;
        this.courseInstructor = courseInstructor;
    }

    // getters and setters

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTiming() {
        return courseTiming;
    }

    public void setCourseTiming(String courseTiming) {
        this.courseTiming = courseTiming;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
}
