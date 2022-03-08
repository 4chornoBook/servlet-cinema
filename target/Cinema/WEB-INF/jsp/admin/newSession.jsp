<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 10.02.2022
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ include file="/WEB-INF/tags/head.jspf"%>
<body>
<jsp:include page="/WEB-INF/common_elements/header.jsp">
	<jsp:param name="title" value="new session"/>
</jsp:include>
<form action="controller?action=addNewMovieSession" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2><fmt:message key="new.session.title"/></h2>
	<label for="movie" class="form-label"><fmt:message key="new.session.movie"/></label>
	<select id="movie" name="movie" class="form-select ${requestScope.movieSelectionError}" aria-describedby="movieSelectionError"
			size="4" aria-label="Default select example">
		<c:forEach items="${applicationScope['movies']}" var="movie">
			<option value="<c:out value="${movie.getId()}"/>"><c:out value="${movie.getName()}"/></option>
		</c:forEach>
	</select>
	<div id="movieSelectionError" class="invalid-feedback">
		<fmt:message key="new.session.choose.movie.error"/>
	</div>
	<div class="mb-3">
		<label for="beginningDate" class="form-label"><fmt:message key="new.session.date"/></label>
		<input type="date" name="movieDate" class="form-control ${requestScope.movieDateError}"
			   aria-describedby="movieDateError"
			   id="beginningDate"
			   required>
		<div id="movieDateError" class="invalid-feedback">
			<fmt:message key="new.session.date.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="beginningTime" class="form-label"><fmt:message key="new.session.beginning.time"/></label>
		<input type="time" name="beginningTime" min="09:00" max="22:00"
			   class="form-control ${beginningTimeError} ${requestScope.slotNotAvailableError}"
			   aria-describedby="beginningTimeError" id="beginningTime"
			   required>
		<div id="movieBeginningTimeHelp" class="form-text">
				<fmt:message key="new.session.ending.time.help"/>
		</div>
		<div id="beginningTimeError" class="invalid-feedback">
					<fmt:message key="new.session.beginning.time.error"/>
		</div>
	</div>
	<button type="submit" class="btn btn-primary"><fmt:message key="new.session.add.session.button"/></button>
</form>
<jsp:include page="/WEB-INF/common_elements/footer.jsp"/>
</body>
</html>
