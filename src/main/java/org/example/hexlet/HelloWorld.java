package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
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

        app.get("/courses/build", ctx -> {
            ctx.render("courses/build.jte");
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
        });


        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = CourseRepository.getEntities()
                    .stream()
                    .filter(c -> c.getId() == Long.parseLong(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot find course with id = " + id)); // db fetch simulation
            var page = new CoursePage(course);
            ctx.render("courses/course.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");

            var courses = CourseRepository.getEntities(); // db fetch simulation

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
