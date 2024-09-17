@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Main paige</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body style="padding-left: 25px">
<main>
    <h1>Main page</h1>
    @if(page.getCurrentUser() != null)
        <p>Добро пожаловать, ${page.getCurrentUser()}.
            <br>Чтобы разлогиниться, удалите куку JSESSIONID из браузера</p>
        <a href="${NamedRoutes.coursesPath()}">Go to courses</a>
    @else
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif
</main>
</body>
</html>
