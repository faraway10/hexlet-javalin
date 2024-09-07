package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.CourseDAO;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = CourseDAO.fetchCourses()
                    .stream()
                    .filter(c -> c.getId() == Long.parseLong(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot find course with id = " + id)); // db fetch simulation
            var page = new CoursePage(course);
            ctx.render("courses/course.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");

            var courses = CourseDAO.fetchCourses(); // db fetch simulation

            if (term != null) {
                courses = courses.stream().filter(course -> course.getName().toLowerCase().contains(term.toLowerCase())).toList();
            }

            var header = "Programming courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
