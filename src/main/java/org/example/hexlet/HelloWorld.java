package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/users", ctx -> ctx.result("Answer for method GET on address /users"));
        app.post("/users", ctx -> ctx.result("Answer for method POST on address /users"));
        app.start(7070); // Стартуем веб-сервер
    }
}
