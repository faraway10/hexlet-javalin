@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.BuildCoursePage
@param BuildCoursePage page

<body style="padding-left: 25px">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Courses</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="${NamedRoutes.coursesPath()}" method="post">
        <div>
            <label>
                Course Name
                <input type="text" required name="name" value="${page.getName()}"/>
            </label>
        </div>
        <div>
            <label>
                Course Description
                <input type="text" required name="description" value="${page.getDescription()}"/>
            </label>
        </div>
        <input type="submit" value="Add" />
    </form>
</body>
