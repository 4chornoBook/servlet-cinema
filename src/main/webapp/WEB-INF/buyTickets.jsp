<%@ taglib prefix="jst" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 15.02.2022
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp"/>
<body style="background-color: #f0f8ff;">
<jsp:include page="/header.jsp"/>
<div style="display: flex; margin-top: 5%">
	<div style="width: 65%; margin: 0 auto">
		<h2>Замовлення</h2>
		<%--		align-items: center--%>
		<div style="display: flex">
			<div>
				<img src="${sessionScope.orderSession.getMovie().getImageURL()}"
					 style="height: 30vh; width:10vw; object-fit: cover;"
					 alt="${sessionScope.orderSession.getMovie().getName()} image">
			</div>
			<div style="font-size: larger; padding-left: 10%">
				<h2><c:out value="${sessionScope.orderSession.getMovie().getName()}"/></h2>
				<p>Дата сеансу: <c:out value="${sessionScope.orderSession.getMovieDate()}"/></p>
				<p>Початок сеансу: <c:out value="${sessionScope.orderSession.getBeginningTime()}"/></p>
			</div>
		</div>
		<c:forEach var="place" items="${sessionScope.orderPlaces}">
			<hr>
			<div style="font-size: large">
				<p>
					Місце №:<c:out value="${place}"/>
				</p>
				<p>
					<c:out value="${sessionScope.orderSession.getMovie().getTicketPrice()}"/>грн.
				</p>
			</div>
			<hr>
		</c:forEach>
		<div style="padding-bottom: 20px; font-size: large"  class="${requestScope.ticketsAlreadyReservedError}"
			 aria-describedby="ticketsAlreadyReservedError" >
			<p style="float: left">Total price:</p>
			<p style="float: right"><c:out value="${sessionScope.totalPrice}"/> грн.</p>
		</div>
		<div style="margin: 30px auto; text-align: center; font-size: larger;" id="ticketsAlreadyReservedError" class="invalid-feedback">
			<p>Tickets already ordered. Return to main page and select available ones</p>
		</div>
	</div>
	<div style="width: 25%; margin: 0 auto; align-items: center">
		<h2>Оплата</h2>
		<form action="controller?action=buyTickets" method="post">
			<div class="mb-3">
				<label for="cardOwner" class="form-label">Card owner</label>
				<input type="text" name="cardOwner" class="form-control ${requestScope.ownerNameError}" id="cardOwner"
					   aria-describedby="cardOwnerError" placeholder="name and surname">
				<div id="cardOwnerError" class="invalid-feedback">
					Bad owner name
				</div>
			</div>
			<div class="mb-3">
				<label for="cardNumber" class="form-label">Card number</label>
				<input type="text" minlength="16" maxlength="16"
					   class="form-control ${requestScope.cardNumberError}"
					   id="cardNumber" name="cardNumber"
					   placeholder="card number" aria-describedby="cardNumberError">
				<div id="cardNumberError" class="invalid-feedback">
					Bad card number
				</div>
			</div>
			<div class="mb-3">
				<label for="cardExpirationDate" class="form-label">Card expiration date</label>
				<input type="text" name="cardExpirationDate"
					   minlength="5" maxlength="5" class="form-control ${requestScope.cardExpirationDateError}"
					   id="cardExpirationDate" placeholder="MM/YY" aria-describedby="cardExpirationDateError">
				<div id="cardExpirationDateError" class="invalid-feedback">
					Bad expiration date
				</div>
			</div>
			<div class="mb-3">
				<label for="cvvCode" class="form-label">CVV code</label>
				<input type="password" maxlength="3" minlength="3" class="form-control ${requestScope.cvvError}"
					   id="cvvCode" placeholder="xxx" name="cvvCode"
					   aria-describedby="cvvError" required>
				<div id="cvvError" class="invalid-feedback">
					bad CVV code
				</div>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">Buy tickets</button>
			</div>
		</form>
	</div>
</div>
</body>
<jsp:include page="/footer.jsp"/>
</html>
