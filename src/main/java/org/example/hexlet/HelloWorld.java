package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/hello", context -> context.result(
                "Hello, "
                + context.queryParamAsClass("name", String.class).getOrDefault("World")
                + "!"));

        app.start(7070); // Стартуем веб-сервер
    }
}
