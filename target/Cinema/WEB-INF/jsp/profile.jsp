<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 16.02.2022
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<body>
<jsp:include page="/header.jsp"/>
<div>
	<h2>User info</h2>
	<p>
		Login:<c:out value="${sessionScope.user.getLogin()}"/>
	</p>
	<p>
		Name:<c:out value="${sessionScope.user.getName()}"/>
	</p>
	<p>
		Surname:<c:out value="${sessionScope.user.getSurname()}"/>
	</p>
</div>
<c:if test="${sessionScope.get('role').name().equals('USER')}">
	<div>
		<h2>Orders history</h2>
		<c:forEach items="${ordersPrices}" var="orderInfo">
			<div style="padding-bottom: 20px">
				<p style="float: left">
					Order <c:out value="${orderInfo.key}"/>
				</p>
				<p style="float: right">
					Price:<c:out value="${orderInfo.value}"/>uah.
				</p>
			</div>
		</c:forEach>
	</div>
</c:if>
</body>
<jsp:include page="/footer.jsp"/>
</html>
