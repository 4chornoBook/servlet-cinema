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
	padding: 10px;
	border-radius: 20px;
	/*margin-left: 5%;*/
	margin: 5% auto;
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
<div>

	<%--<table>--%>
	<%--	<c:forEach var="i" begin="1" end="100">--%>
	<%--		<c:if test="${(i-1) % 10 == 0}">--%>
	<%--			<tr>--%>
	<%--		</c:if>--%>
	<%--		<td>--%>
	<%--			<c:choose>--%>
	<%--				<c:when test="${ticketsNumber.contains(i)}">--%>
	<%--					<p style="color: red"><c:out value="${i}"/> ticket</p>--%>
	<%--				</c:when>--%>
	<%--				<c:otherwise>--%>
	<%--					<p style="color: green"><c:out value="${i}"/> ticket</p>--%>
	<%--				</c:otherwise>--%>
	<%--			</c:choose>--%>
	<%--		</td>--%>
	<%--		<c:if test="${(i-1) % 10 == 9}">--%>
	<%--			</tr>--%>
	<%--		</c:if>--%>
	<%--	</c:forEach>--%>
	<%--</table>--%>
	<%--<style>--%>
	<%--	#reserved {--%>
	<%--		background-color: grey;--%>
	<%--        width: 4%;--%>
	<%--        height: 4%;--%>
	<%--	}--%>
	<%--	.btn-primary {--%>
	<%--	}--%>
	<%--</style>--%>
	<c:if test="${!sessionScope.get('role').name().equals('ADMIN')}">
	<form action="controller?action=buyTickets">
		</c:if>
		<table style="margin: 0 auto">
			<c:forEach var="i" begin="1" end="100">
				<c:if test="${(i-1) % 10 == 0}">
					<tr>
				</c:if>
				<td>
					<c:choose>
						<c:when test="${ticketsNumber.contains(i)}">
							<%--						<button type="button" value="<c:out value="${i}"/>" disabled class="btn btn-secondary btn-lg"--%>
							<button type="button" value="<c:out value="${i}"/>" disabled
									class="btn btn-secondary"
									data-bs-toggle="button" autocomplete="off">
								<c:out value="${i}"/>
								<br>
								місце
							</button>
						</c:when>
						<c:otherwise>
							<button type="button" value="<c:out value="${i}"/>" class="btn btn-outline-primary"
									data-bs-toggle="button" autocomplete="off">
								<c:out value="${i}"/>
								<br>
								місце
							</button>
						</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${(i-1) % 10 == 9}">
					</tr>
				</c:if>
			</c:forEach>

		</table>
		<c:choose>
		<c:when test="${sessionScope.get('role').name().equals('ADMIN')}">
			<form action="controller?action=removeMovieSession">
				<div class="text-center">
					<button type="submit" class="w-50 btn btn-outline-danger">
						Delete Session
					</button>
				</div>
			</form>
		</c:when>
		<c:otherwise>
		<div class="text-center">
			<input class="w-50 btn btn-outline-success mx-auto" type="submit" value="order tickets">
		</div>
	</form>
	</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
