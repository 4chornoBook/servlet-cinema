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
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
<form action="controller?action=addNewMovie" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2>New movie</h2>
	<div class="mb-3">
		<label for="exampleInputName" class="form-label ${requestScope.movieNameError}">Movie name</label>
		<input type="text" name="name" minlength="1" maxlength="50" class="form-control" id="exampleInputName"
			   aria-describedby="userError" required>
		<div id="userError" class="invalid-feedback">
			Enter movieName
		</div>
	</div>
	<div class="mb-3">
		<label for="exampleDate" class="form-label">Release date</label>
		<input type="date" name="releaseDate" class="form-control ${requestScope.releaseDateError}" id="exampleDate"
			   aria-describedby="dateError" required>
		<div id="name" class="form-text">set release date</div>
		<div id="dateError" class="invalid-feedback">
			incorrect date
		</div>
	</div>

	<div class="mb-3">
		<label for="exampleLength" class="form-label">Length</label>
		<input type="number" min="20" max="600" step="1" value="90" name="length"
			   class="form-control ${requestScope.lengthError}"
			   id="exampleLength"
			   aria-describedby="lengthError" required>
		<div id="surname" class="form-text">at least 1 symbol</div>
		<div id="lengthError" class="invalid-feedback">
			Enter movieName
		</div>
	</div>
	<div style="margin-bottom: 20px;">
		<label for="genres" class="form-label">Genres</label>
		<hr>
		<div id="genres" class="${requestScope.genresError}" aria-describedby="genresError"
			 style="height: 20%; overflow: auto;">
			<c:forEach items="${applicationScope['genres']}" var="genre">
				<div class="form-check">
					<input class="form-check-input" name="genre" type="checkbox"
						   value="<c:out value="${genre.getName()}"/>"
						   id="genre">
					<label class="form-check-label" for="genre">
						<c:out value="${genre.getName()}"/>
					</label>
				</div>
			</c:forEach>
		</div>
		<hr>
		<div id="genresError" class="invalid-feedback">
			<p>Choose at least one genre </p>
		</div>

	</div>
	<%--drop down--%>
	<div class="mb-3">
		<label for="imageURL" class="form-label">Image URL</label>
		<input type="text" name="imageURL" class="form-control ${requestScope.imageURLError}" id="imageURL"
			   aria-describedby="imageURLError" required>
		<div id="imageHelp" class="form-text">paste url on movie poster</div>
		<div id="imageURLError" class="invalid-feedback">
			Enter image URL
		</div>
	</div>
	<div class="mb-3">
		<label for="ticketPrice" class="form-label">Ticket price</label>
		<input type="number" min="50" step="5" value="50" name="ticketPrice"
			   class="form-control ${requestScope.ticketPriceError}"
			   id="ticketPrice" aria-describedby="ticketPriceError">
		<div id="ticketPriceError" class="invalid-feedback">
			Set price for the ticket (at least 50)
		</div>
	</div>
	<div class="mb-3">
		<label for="exampleFormControlTextarea1" class="form-label">Description</label>
		<textarea class="form-control ${requestScope.descriptionError}" name="description"
				  id="exampleFormControlTextarea1" aria-describedby="descriptionError" rows="3"></textarea>
		<div id="descriptionError" class="invalid-feedback">
			Add description to the movie
		</div>
	</div>
	<button type="submit" class="btn btn-primary">Add movie</button>
	<jsp:include page="/footer.jsp"/>
</body>
</html>
