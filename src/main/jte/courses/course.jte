@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page
<body style="padding-left: 25px">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Courses</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <main>
        <h1>${page.getCourse().getName()}</h1>
        <p>${page.getCourse().getDescription()}</p>
    </main>
</body>
