package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {
    public static void index(Context ctx) {
        var term = ctx.queryParam("term");

        var courses = CourseRepository.getEntities(); // db fetch simulation

        if (term != null) {
            courses = courses.stream().filter(course -> course.getName().toLowerCase().contains(term.toLowerCase())).toList();
        }

        var page = new CoursesPage(courses, "Programming courses");
        ctx.render("courses/courses.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParam("id");
        var course = CourseRepository.getEntities()
                .stream()
                .filter(c -> c.getId() == Long.parseLong(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find course with id = " + id)); // db fetch simulation
        var page = new CoursePage(course);
        ctx.render("courses/course.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
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
    }
}
