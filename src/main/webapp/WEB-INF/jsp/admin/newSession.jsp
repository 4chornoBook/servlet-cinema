<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 10.02.2022
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New session</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<form action="controller?action=addNewMovieSession" method="post">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
            Select movie
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <c:forEach items="${applicationScope['movies']}" var="movie">
                <li>
                    <div class="form-check">
                        <input class="form-check-input" name="movie" type="radio"
                               value="<c:out value="${movie.getId()}"/>"
                               id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            <c:out value="${movie.getName()}"/>
                        </label>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <p>Beginning date </p>
    <input type="date" name="movieDate">
    <br>
    <p>Beginning time</p>
    <input type="time" name="beginningTime" min="09:00" max="22:00">
    <br>
    <p>Ending time</p>
    <input type="time" name="endingTime" required>
    <br>
    <br>
    <input type="submit" name="add new session">
</form>
</body>
</html>
