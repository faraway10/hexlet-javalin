package org.example.hexlet.model;

import java.util.List;
public class CourseDAO {
    public static List<Course> fetchCourses() {
        return List.of( // db fetch simulation
                new Course(1L,"Javalin", "Course about Javalin"),
                new Course(2L,"Java Spring Boot", "Course about Java Spring Boot"),
                new Course(3L,"SQL", "Course about SQL"),
                new Course(4L,"Python", "Python")
        );
    }
}
