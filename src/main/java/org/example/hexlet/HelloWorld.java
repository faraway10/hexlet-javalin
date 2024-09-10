package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
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

        app.get("/", ctx -> {
            ctx.redirect(NamedRoutes.coursesPath());
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", model("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            try {
                name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Course name must be longer than 2 characters")
                        .get();

                description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Course description must be longer than 10 characters")
                        .get();

                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", model("page", page));
            }

        });


        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParam("id");
            var course = CourseRepository.getEntities()
                    .stream()
                    .filter(c -> c.getId() == Long.parseLong(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot find course with id = " + id)); // db fetch simulation
            var page = new CoursePage(course);
            ctx.render("courses/course.jte", model("page", page));
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
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
