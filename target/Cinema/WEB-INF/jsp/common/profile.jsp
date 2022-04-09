<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 16.02.2022
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ include file="/WEB-INF/tags/head.jspf"%>
<body>
<jsp:include page="/WEB-INF/common_elements/header.jsp">
	<jsp:param name="title" value="profile"/>
</jsp:include>
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
	<div style="font-size: larger;  width: 70%; margin: 2% auto">
		<h2><fmt:message key="profile.orders.history"/></h2>
		<c:forEach items="${userOrders}" var="order">
			<div style="display: flex; justify-content: space-between; border-radius: 15px; border: 2px solid darkblue; background-color: lavender;margin: 20px auto; padding: 10px;">
				<p>
					<fmt:message key="profile.orders.order.number"/> <c:out value="${order.getId()}"/>
				</p>
				<p>
					<fmt:message key="profile.orders.order.price"/> <c:out value="${order.getTotalPrice()}"/> <fmt:message key="profile.orders.price.currency"/>
				</p>
			</div>
		</c:forEach>
		<c:if test="${userHasNoOrders}">
			<div style="justify-content: space-between; border-radius: 15px; border: 2px solid darkblue; background-color: lavender;margin: 20px auto; padding: 10px;">
				<p  style="text-align: center;"><fmt:message key="profile.no.oders"/></p>
			</div>
		</c:if>
	</div>
<jsp:include page="/WEB-INF/common_elements/footer.jsp"/>
</body>
</html>
