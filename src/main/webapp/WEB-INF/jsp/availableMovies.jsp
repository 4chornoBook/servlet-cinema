<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 12.02.2022
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${applicationScope['availableSessions']}" var="session">
	<div class="movie" style="border: 2px black solid">
		<h3><c:out value="${session.getMovie().getName()}"/></h3>
		<img src="${session.getMovie().getImageURL()}" alt="${session.getMovie().getName()} image">
		<p><c:out value="${session.getMovie().getDescription()}"/></p>
		<p>Price:<c:out value="${session.getMovie().getTicketPrice()}"/>uah</p>
		<p>Length:<c:out value="${session.getMovie().getLengthInMinutes()}"/></p>
		<p>
			<c:forEach items="${session.getMovie().getGenres()}" var="genre">
				<c:out value="${genre.getName()}"/>,
			</c:forEach>
		</p>
		<form action="controller?action=showMovieSession" method="post">
			<input type="hidden" value="${session.getId()}" name="sessionId">
			<input type="submit" name="submit" value="buy ticket">
		</form>
	</div>
</c:forEach>
