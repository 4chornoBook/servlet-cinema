<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 07.02.2022
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<jsp:include page="/header.jsp"/>
<%--<head>--%>
<%--    <title>Cinema user</title>--%>
<%--</head>--%>
<body>
    <h2>Hello user</h2>
    <jsp:include page="/WEB-INF/jsp/availableMovies.jsp"/>
</body>
</html>
