<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 09.02.2022
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>New movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<form action="controller?action=addNewMovie" method="post">
    <p>movie name</p>
    <input type="text" name="name" placeholder="movie name">
    <br>
    <br>
    <p>Release date</p>
    <input type="date" name="releaseDate">
    <br>
    <br>
    <p>Length in minutes</p>
    <input type="number" name="length" min="20" max="600" step="1" value="90">
    <br>
    <br>
    <%--    here will be checkboxes for genres--%>
    <%--    <select class="select" multiple>--%>
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown button
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <c:forEach items="${applicationScope['genres']}" var="genre">
                <%--            <c:forEach items="${requestScope.genres}" var="genre">--%>
                <li>
                    <div class="form-check">
                        <input class="form-check-input" name="genre" type="checkbox"
                               value="<c:out value="${genre.getName()}"/>"
                               id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            <c:out value="${genre.getName()}"/>
                        </label>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <p>Image url</p>
    <input type="text" name="imageURL">
    <br>
    <br>
    <p>Ticket price</p>
    <input type="number" name="ticketPrice" step="5" min="50">
    <br>
    <br>
    <p>description</p>
    <textarea name="description" id="description" cols="30" rows="10"></textarea>
    <input type="submit" value="add movie">
</form>
</body>
</html>
