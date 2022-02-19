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
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
<form action="controller?action=addNewMovieSession" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2>New session</h2>
	<select name="movie" class="form-select ${requestScope.movieSelectionError}" aria-describedby="movieSelectionError"
			size="4" aria-label="Default select example">
		<option value="">Choose movie for session</option>
		<c:forEach items="${applicationScope['movies']}" var="movie">
			<option value="<c:out value="${movie.getId()}"/>"><c:out value="${movie.getName()}"/></option>
		</c:forEach>
	</select>
	<div id="movieSelectionError" class="invalid-feedback">
		Select movie for session
	</div>
	<div class="mb-3">
		<label for="beginningDate" class="form-label">Session date</label>
		<input type="date" name="movieDate" class="form-control ${requestScope.movieDateError}"
			   aria-describedby="movieDateError"
			   id="beginningDate"
			   required>
		<div id="movieDateError" class="invalid-feedback">
			bad session date
		</div>
	</div>
	<div class="mb-3">
		<label for="beginningTime" class="form-label">Beginning time</label>
		<input type="time" name="beginningTime" min="09:00" max="22:00"
			   class="form-control ${beginningTimeError} ${requestScope.slotNotAvailableError}"
			   aria-describedby="beginningTimeError" id="beginningTime"
			   required>
		<div id="movieBeginningTimeHelp" class="form-text">Ending time will be set automatically depending on movie
			length and cleaning time
		</div>
		<div id="beginningTimeError" class="invalid-feedback">
			bad beginning time.(Maybe date is already passed or slot is reserved)
		</div>
	</div>
	<button type="submit" class="btn btn-primary">Add session</button>
</form>
<jsp:include page="/footer.jsp"/>
</body>
</html>
