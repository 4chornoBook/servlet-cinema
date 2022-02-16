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
<%--<head>--%>
<%--    <title>New movie</title>--%>
<%--</head>--%>
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
<%--<form action="controller?action=addNewMovie" method="post">--%>
<%--	<p>movie name</p>--%>
<%--	<input type="text" name="name" placeholder="movie name">--%>
<%--	<br>--%>
<%--	<br>--%>
<%--	<p>Release date</p>--%>
<%--	<input type="date" name="releaseDate">--%>
<%--	<br>--%>
<%--	<br>--%>
<%--	<p>Length in minutes</p>--%>
<%--	<input type="number" name="length" min="20" max="600" step="1" value="90">--%>
<%--	<br>--%>
<%--	<br>--%>
<%--	&lt;%&ndash;    here will be checkboxes for genres&ndash;%&gt;--%>
<%--	&lt;%&ndash;    <select class="select" multiple>&ndash;%&gt;--%>
<%--	<div class="dropdown">--%>
<%--		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"--%>
<%--				data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--			Dropdown button--%>
<%--		</button>--%>
<%--		<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">--%>
<%--			<c:forEach items="${applicationScope['genres']}" var="genre">--%>
<%--				&lt;%&ndash;            <c:forEach items="${requestScope.genres}" var="genre">&ndash;%&gt;--%>
<%--				<li>--%>
<%--					<div class="form-check">--%>
<%--						<input class="form-check-input" name="genre" type="checkbox"--%>
<%--							   value="<c:out value="${genre.getName()}"/>"--%>
<%--							   id="flexCheckChecked">--%>
<%--						<label class="form-check-label" for="flexCheckChecked">--%>
<%--							<c:out value="${genre.getName()}"/>--%>
<%--						</label>--%>
<%--					</div>--%>
<%--				</li>--%>
<%--			</c:forEach>--%>
<%--		</ul>--%>
<%--	</div>--%>
<%--	<p>Image url</p>--%>
<%--	<input type="text" name="imageURL">--%>
<%--	<br>--%>
<%--	<br>--%>
<%--	<p>Ticket price</p>--%>
<%--	<input type="number" name="ticketPrice" step="5" min="50">--%>
<%--	<br>--%>
<%--	<br>--%>
<%--	<p>description</p>--%>
<%--	<textarea name="description" id="description" cols="30" rows="10"></textarea>--%>
<%--	<input type="submit" value="add movie">--%>
<%--</form>--%>

<%--<div style="height: 100%">block</div>--%>

<form action="controller?action=addNewMovie" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2>New movie</h2>
	<div class="mb-3">
		<label for="exampleInputName" class="form-label">Movie name</label>
		<input type="text" name="name" minlength="1" maxlength="50" class="form-control" id="exampleInputName"
			   aria-describedby="nameHelp">
		<%--		<div id="nameHelp" class="form-text">u</div>--%>
	</div>
	<div class="mb-3">
		<label for="exampleDate" class="form-label">Release date</label>
		<input type="date" name="releaseDate" class="form-control" id="exampleDate"
			   aria-describedby="emailHelp">
		<div id="name" class="form-text">set release date</div>
	</div>

	<div class="mb-3">
		<label for="exampleLength" class="form-label">Length</label>
		<input type="number" min="20" max="600" step="1" value="90" name="length" class="form-control"
			   id="exampleLength"
			   aria-describedby="emailHelp">
		<div id="surname" class="form-text">at least 1 symbol</div>
	</div>
	<div style="margin-bottom: 20px;">
		<label for="genres" class="form-label">Genres</label>
		<hr>
		<div id="genres" style="height: 20%; overflow: auto;">
			<c:forEach items="${applicationScope['genres']}" var="genre">
				<div class="form-check">
					<input class="form-check-input" name="genre" type="checkbox"
						   value="<c:out value="${genre.getName()}"/>"
						   id="flexCheckChecked1">
					<label class="form-check-label" for="flexCheckChecked1">
						<c:out value="${genre.getName()}"/>
					</label>
				</div>
			</c:forEach>
		</div>
		<hr>
	</div>
	<%--drop down--%>
	<div class="mb-3">
		<label for="imageURL" class="form-label">Image URL</label>
		<input type="text" name="imageURL" class="form-control" id="imageURL"
			   aria-describedby="imageHelp">
		<div id="imageHelp" class="form-text">paste url on movie poster</div>
	</div>
	<div class="mb-3">
		<label for="ticketPrice" class="form-label">Ticket price</label>
		<input type="number" min="50" step="5" value="50" name="ticketPrice" class="form-control"
			   id="ticketPrice">
	</div>
	<div class="mb-3">
		<label for="exampleFormControlTextarea1" class="form-label">Description</label>
		<textarea class="form-control" name="description" id="exampleFormControlTextarea1" rows="3"></textarea>
	</div>
	<button type="submit" class="btn btn-primary">Add movie</button>
</form>
<jsp:include page="/footer.jsp"/>
</body>
</html>
