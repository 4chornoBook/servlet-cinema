<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 09.02.2022
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ include file="/WEB-INF/tags/head.jspf"%>
<body>
<jsp:include page="/WEB-INF/common_elements/header.jsp">
	<jsp:param name="title" value="new movie"/>
</jsp:include>
<form action="controller?action=addNewMovie" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2><fmt:message key="new.movie.title"/></h2>
	<div class="mb-3">
		<label for="exampleInputName" class="form-label ${requestScope.movieNameError}"><fmt:message key="new.movie.movie.name"/></label>
		<input type="text" name="name" minlength="1" maxlength="50" class="form-control" id="exampleInputName"
			   aria-describedby="userError" required>
		<div id="userError" class="invalid-feedback">
			<fmt:message key="new.movie.movie.name.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="exampleDate" class="form-label"><fmt:message key="new.movie.release.date"/></label>
		<input type="date" name="releaseDate" class="form-control ${requestScope.releaseDateError}" id="exampleDate"
			   aria-describedby="dateError" required>
		<div id="name" class="form-text"><fmt:message key="new.movie.release.date.help"/></div>
		<div id="dateError" class="invalid-feedback">
			<fmt:message key="new.movie.release.date.error"/>
		</div>
	</div>

	<div class="mb-3">
		<label for="exampleLength" class="form-label"><fmt:message key="new.movie.length"/></label>
		<input type="number" min="20" max="600" step="1" value="90" name="length"
			   class="form-control ${requestScope.lengthError}"
			   id="exampleLength"
			   aria-describedby="lengthError" required>
		<div id="lengthError" class="invalid-feedback">
			<fmt:message key="new.movie.length.error"/>
		</div>
	</div>
	<div style="margin-bottom: 20px;">
		<label for="genres" class="form-label"><fmt:message key="new.movie.genres"/></label>
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
			<p><fmt:message key="new.movie.genres.error"/></p>
		</div>

	</div>
	<%--drop down--%>
	<div class="mb-3">
		<label for="imageURL" class="form-label"><fmt:message key="new.movie.image.url"/></label>
		<input type="text" name="imageURL" class="form-control ${requestScope.imageURLError}" id="imageURL"
			   aria-describedby="imageURLError" required>
		<div id="imageHelp" class="form-text"><fmt:message key="new.movie.image.url.help"/></div>
		<div id="imageURLError" class="invalid-feedback">
			<fmt:message key="new.movie.image.url.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="ticketPrice" class="form-label"><fmt:message key="new.movie.ticket.price"/></label>
		<input type="number" min="50" step="5" value="50" name="ticketPrice"
			   class="form-control ${requestScope.ticketPriceError}"
			   id="ticketPrice" aria-describedby="ticketPriceError">
		<div id="ticketPriceError" class="invalid-feedback">
			<fmt:message key="new.movie.ticket.price.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="exampleFormControlTextarea1" class="form-label"><fmt:message key="new.movie.description"/></label>
		<textarea class="form-control ${requestScope.descriptionError}" name="description"
				  id="exampleFormControlTextarea1" aria-describedby="descriptionError" rows="3"></textarea>
		<div id="descriptionError" class="invalid-feedback">
			<fmt:message key="new.movie.description.error"/>
		</div>
	</div>
	<button type="submit" class="btn btn-primary"><fmt:message key="new.movie.add.movie.button"/> </button>
	<jsp:include page="/WEB-INF/common_elements/footer.jsp"/>
</body>
</html>
