@import org.example.hexlet.dto.courses.BuildCoursePage
@param BuildCoursePage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/courses" method="post">
    <div>
        <label>
            Course Name
            <input type="text" required name="name" />
        </label>
    </div>
    <div>
        <label>
            Course Description
            <input type="text" required name="description" />
        </label>
    </div>
    <input type="submit" value="Add" />
</form>
