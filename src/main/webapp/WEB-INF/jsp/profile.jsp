<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 16.02.2022
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<body>
<jsp:include page="/header.jsp"/>
<div style="font-size: larger; border: 2px solid darkblue; background-color: lavender; padding: 10px; width:70%;margin: 5% auto; border-radius: 15px; align-items: center">
	<h2><fmt:message key="profile.user.info"/></h2>
	<p>
		<fmt:message key="profile.login"/> <c:out value="${sessionScope.user.getLogin()}"/>
	</p>
	<p>
		<fmt:message key="profile.name"/> <c:out value="${sessionScope.user.getName()}"/>
	</p>
	<p>
		<fmt:message key="profile.surname"/> <c:out value="${sessionScope.user.getSurname()}"/>
	</p>
</div>
<c:if test="${sessionScope.get('role').name().equals('USER')}">
	<div style="font-size: larger;  width: 70%; margin: 2% auto">
		<h2><fmt:message key="profile.orders.history"/></h2>
		<c:forEach items="${ordersPrices}" var="orderInfo">
			<div style="display: flex; justify-content: space-between; border-radius: 15px; border: 2px solid darkblue; background-color: lavender;margin: 20px auto; padding: 10px;">
				<p>
					<fmt:message key="profile.orders.order.number"/> <c:out value="${orderInfo.key}"/>
				</p>
				<p>
					<fmt:message key="profile.orders.order.price"/> <c:out value="${orderInfo.value}"/>uah.
				</p>
			</div>
		</c:forEach>
	</div>
</c:if>
</body>
<jsp:include page="/footer.jsp"/>
</html>
