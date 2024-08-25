package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = new Course("course " + id, "description " + id); // db fetch simulation
            var page = new CoursePage(course);
            ctx.render("courses/course.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var courses = List.of( // db fetch simulation
                    new Course(1L,"Javalin", "Course about Javalin"),
                    new Course(2L,"Spring Boot", "Course about Spring Boot")
            );
            var header = "Programming courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
