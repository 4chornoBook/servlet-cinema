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
<%@ include file="/WEB-INF/tags/head.jspf"%>

<jsp:include page="/WEB-INF/common_elements/header.jsp"/>
<body>
    <jsp:include page="/WEB-INF/jsp/common/availableMovies.jsp"/>
</body>
</html>
