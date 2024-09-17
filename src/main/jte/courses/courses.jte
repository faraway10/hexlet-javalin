@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body style="padding-left: 25px">
<h1>${page.getHeader()}</h1>
<form action="${NamedRoutes.coursesPath()}" method="get">
    <input type="search" required name="term">
    <input type="submit" value="Search">
</form>
<br>
<a href="${NamedRoutes.buildCoursePath()}">Add Course</a>
@if(page.getFlash() != null)
    <p class="alert alert-primary" role="alert">${page.getFlash()}</p>
@endif
@if(page.getCourses().isEmpty())
    <p>Still no courses</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
</body>
</html>
