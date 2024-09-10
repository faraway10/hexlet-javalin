package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

public class HelloWorld {
    public static void main(String[] args) {
        CourseRepository.save(new Course("Javalin", "Course about Javalin"));
        CourseRepository.save(new Course("Java Spring Boot", "Course about Java Spring Boot"));
        CourseRepository.save(new Course("SQL", "Course about SQL"));
        CourseRepository.save(new Course("Python", "Course about Python"));

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {ctx.redirect(NamedRoutes.coursesPath());});

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.coursesPath() + "/{id}", CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.start(7070);
    }
}
