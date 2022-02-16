<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 07.02.2022
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--	<title>Cinema admin</title>--%>
<%--</head>--%>
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
</form>
<jsp:include page="/WEB-INF/jsp/availableMovies.jsp"/>
<%--print all movies--%>
<%--<c:forEach items="${applicationScope['availableSessions']}" var="session">--%>
<%--	<div class="movie">--%>
<%--		<h3><c:out value="${session.getMovie().getName()}"/></h3>--%>
<%--		<img src="${session.getMovie().getImageURL()}" alt="${session.getMovie().getName()} image">--%>
<%--		<p><c:out value="${session.getMovie().getDescription()}"/></p>--%>
<%--		<p>Price:<c:out value="${session.getMovie().getTicketPrice()}"/>uah</p>--%>
<%--		<p>Length:<c:out value="${session.getMovie().getLengthInMinutes()}"/></p>--%>
<%--		<p>--%>
<%--			<c:forEach items="${session.getMovie().getGenres()}" var="genre">--%>
<%--				<c:out value="${genre.getName()}"/>,--%>
<%--			</c:forEach>--%>
<%--        </p>--%>
<%--	</div>--%>
<%--</c:forEach>--%>
<jsp:include page="/footer.jsp"/>
</body>
</html>
