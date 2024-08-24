package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/users/{id}/post/{postId}", context -> {
            var userId = context.pathParam("id");
            var postId = context.pathParam("postId");
            context.result(
                    "userId: " + userId + "\n"
                    + "postId: " + postId
            );
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
