@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<h1>${page.getHeader()}</h1>
<form action="/courses" method="get">
    <input type="search" required name="term">
    <input type="submit" value="Search">
</form>
<br>
<a href="courses/build">Add Course</a>
@if(page.getCourses().isEmpty())
    <p>Still no courses</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
</body>
</html>
