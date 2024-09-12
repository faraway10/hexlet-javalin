package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

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

        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("courses/index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.coursesPath() + "/{id}", CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.start(7070);
    }
}
