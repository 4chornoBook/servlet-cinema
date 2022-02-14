<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 12.02.2022
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%--todo set tittle--%>
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
<%--<h1><c:out value="${requestScope.session.getMovie().getName()}"/></h1>--%>
<%--<h2><c:out value="${requestScope.session.getMovieDate()}"/></h2>--%>
<%--<h2><c:out value="${requestScope.session.getBeginningTime()}"/></h2>--%>
<%--<h2><c:out value="${requestScope.session.getEndingTime()}"/></h2>--%>
<%--<img src="<c:out value="${requestScope.session.getMovie().getImageURL()}"/>" alt="movie poster">--%>
<%--<c:if test="${sessionScope.role != null && sessionScope.role.name().equals('ADMIN')}">--%>
<%--	<form action="controller?action=removeMovieSession" method="post">--%>
<%--		<input type="submit">--%>
<%--		<input type="hidden" name="sessionId" value="<c:out value="${requestScope.session.getId()}"/>">--%>
<%--		<input type="submit" value="remove session">--%>
<%--	</form>--%>
<%--</c:if>--%>
<%--<form action="controller?action=buyTickets" method="post">--%>
<%--	<input type="submit" value="choose tickets">--%>
<%--	<input type="hidden" name="action" value="">--%>
<%--</form>--%>

<%--<div style="height: 1000px"></div>--%>


<div class="movie" style="
	border: 2px darkblue solid; width: auto; display: flex; width: 70%; align-items: center;
	background-color: #f0f8ff;
	margin-left: 5%;
	margin-top: 5%;
	">
	<div>
		<img src="${session.getMovie().getImageURL()}"
			 style="height: 60vh; width:20vw; object-fit: cover;" alt="${session.getMovie().getName()} image">
	</div>
	<div style="font-size: larger; padding-left: 10%">
		<h2><c:out value="${session.getMovie().getName()}"/></h2>
		<p>Дата показу: <c:out value="${session.getMovieDate()}"/></p>
		<p>Час показу: <c:out value="${session.getBeginningTime()}"/> - <c:out
				value="${session.getEndingTime()}"/></p>
		<p>Ціна квитка:<c:out value="${session.getMovie().getTicketPrice()}"/>грн.</p>
		<p>Тривалість: <c:out value="${session.getMovie().getLengthInMinutes()}"/> хв.</p>
		<p>Жанр:
			<%--			todo show genres with delimeter comma (create toString method for genre and
			in output set list and replace square brackets--%>
			<c:forEach items="${session.getMovie().getGenres()}" var="genre">
				<c:out value="${genre.getName()}"/>;
			</c:forEach>
		</p>
		<p>
			<c:out value="${session.getMovie().getDescription()}"/>
		</p>
		<form action="controller?action=showMovieSession" method="post">
			<input type="hidden" value="${session.getId()}" name="sessionId">
		</form>
	</div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
