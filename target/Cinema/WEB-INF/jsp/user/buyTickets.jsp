<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 15.02.2022
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tags/head.jspf"%>
<html>
<body style="background-color: #f0f8ff;">
<jsp:include page="/WEB-INF/common_elements/header.jsp">
	<jsp:param name="title" value="buy tickets"/>
</jsp:include>
<div style="display: flex; margin-top: 5%">
	<div style="width: 65%; margin: 0 auto">
		<h2><fmt:message key="buy.tickets.title"/></h2>
		<%--		align-items: center--%>
		<div style="display: flex">
			<div>
				<img src="${sessionScope.orderSession.getMovie().getImageURL()}"
					 style="height: 30vh; width:10vw; object-fit: cover;"
					 alt="${sessionScope.orderSession.getMovie().getName()} image">
			</div>
			<div style="font-size: larger; padding-left: 10%">
				<h2><c:out value="${sessionScope.orderSession.getMovie().getName()}"/></h2>
				<p><fmt:message key="buy.tickets.date"/> <c:out
						value="${sessionScope.orderSession.getMovieDate()}"/></p>
				<p><fmt:message key="buy.tickets.time"/> <c:out
						value="${sessionScope.orderSession.getBeginningTime()}"/></p>
			</div>
		</div>
		<c:forEach var="place" items="${sessionScope.orderPlaces}">
			<hr>
			<div style="font-size: large">
				<p>
					<fmt:message key="buy.tickets.place"/> <c:out value="${place}"/>
				</p>
				<p>
					<c:out value="${sessionScope.orderSession.getMovie().getTicketPrice()}"/> <fmt:message
						key="buy.tickets.currency"/>
				</p>
			</div>
			<hr>
		</c:forEach>
		<div style="padding-bottom: 20px; font-size: large" class="${requestScope.ticketsAlreadyReservedError}"
			 aria-describedby="ticketsAlreadyReservedError">
			<p style="float: left"><fmt:message key="buy.tickets.total.price"/></p>
			<p style="float: right"><c:out value="${sessionScope.totalPrice}"/> <fmt:message
					key="buy.tickets.currency"/></p>
		</div>
		<div style="margin: 30px auto; text-align: center; font-size: larger;" id="ticketsAlreadyReservedError"
			 class="invalid-feedback"><p>
			<fmt:message key="buy.tickets.already.reserved.error"/></p>
		</div>
	</div>
	<div style="width: 25%; margin: 0 auto; align-items: center">
		<h2><fmt:message key="buy.tickets.payment"/></h2>
		<form action="controller?action=buyTickets" method="post">
			<div class="mb-3">
				<label for="cardOwner" class="form-label"><fmt:message key="buy.tickets.card.owner"/></label>
				<input type="text" name="cardOwner" class="form-control ${requestScope.ownerNameError}" id="cardOwner"
					   aria-describedby="cardOwnerError" placeholder="name and surname">
				<div id="cardOwnerError" class="invalid-feedback">
					<fmt:message key="buy.tickets.card.owner.error"/>
				</div>
			</div>
			<div class="mb-3">
				<label for="cardNumber" class="form-label"><fmt:message key="buy.tickets.card.number"/></label>
				<input type="text" minlength="16" maxlength="16"
					   class="form-control ${requestScope.cardNumberError}"
					   id="cardNumber" name="cardNumber"
					   placeholder="card number" aria-describedby="cardNumberError">
				<div id="cardNumberError" class="invalid-feedback">
					<fmt:message key="buy.tickets.card.number.error"/>
				</div>
			</div>
			<div class="mb-3">
				<label for="cardExpirationDate" class="form-label"><fmt:message key="buy.tickets.card.expiration.date"/></label>
				<input type="text" name="cardExpirationDate"
					   minlength="5" maxlength="5" class="form-control ${requestScope.cardExpirationDateError}"
					   id="cardExpirationDate" placeholder="MM/YY" aria-describedby="cardExpirationDateError">
				<div id="cardExpirationDateError" class="invalid-feedback">
					<fmt:message key="buy.tickets.card.expiration.date.error"/>
				</div>
			</div>
			<div class="mb-3">
				<label for="cvvCode" class="form-label"><fmt:message key="buy.tickets.card.cvv.code"/></label>
				<input type="password" maxlength="3" minlength="3" class="form-control ${requestScope.cvvError}"
					   id="cvvCode" placeholder="xxx" name="cvvCode"
					   aria-describedby="cvvError" required>
				<div id="cvvError" class="invalid-feedback">
					<fmt:message key="buy.tickets.card.cvv.code.error"/>
				</div>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3"><fmt:message key="buy.ticket.buy.button"/></button>
			</div>
		</form>
	</div>
</div>
</body>
<jsp:include page="/WEB-INF/common_elements/footer.jsp"/>
</html>
