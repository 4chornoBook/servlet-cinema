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
<head>
	<title><c:out value="${requestScope.session.getMovie().getName()}"/></title>
</head>
<body>
<h1><c:out value="${requestScope.session.getMovie().getName()}"/></h1>
<h2><c:out value="${requestScope.session.getMovieDate()}"/></h2>
<h2><c:out value="${requestScope.session.getBeginningTime()}"/></h2>
<h2><c:out value="${requestScope.session.getEndingTime()}"/></h2>
<img src="<c:out value="${requestScope.session.getMovie().getImageURL()}"/>" alt="movie poster">
</body>
</html>
