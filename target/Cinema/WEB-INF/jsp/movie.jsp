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
	</div>
</div>
<div>
	<c:if test="${!sessionScope.get('role').name().equals('ADMIN')}">
	<form action="controller?action=submitOrder" id="buyTickets" method="post">
		<input type="hidden" form="buyTickets" value="${session.getId()}" name="sessionId">
		</c:if>
		<table style="margin: 0 auto">
			<c:forEach var="i" begin="1" end="100">
				<c:if test="${(i-1) % 10 == 0}">
					<tr>
				</c:if>
				<td>
					<c:choose>
						<c:when test="${ticketsNumber.contains(i)}">
							<input type="checkbox" name="numberPlace" disabled class="btn-check" id="btn-check${i}"
								   autocomplete="off"/>
							<label class="btn btn-secondary" for="btn-check${i}"><c:out value="${i}"/><br>місце</label>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="numberPlace" value="${i}" class="btn-check" id="btn-check${i}"
								   autocomplete="off"/>
							<label class="btn btn-outline-primary" for="btn-check${i}"><c:out
									value="${i}"/><br>місце</label>
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
			<form action="controller?action=removeMovieSession" id="removeSession" method="post">
				<div class="text-center">
					<button type="button" form="removeSession" class="w-50 btn btn-outline-danger"
							data-bs-toggle="modal"
							data-bs-target="#myModal">
						Delete Session
					</button>
					<div class="modal" id="myModal" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Попередження!</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<p>Ви дійсно хочете видалити показ фільму. Квитки всіх клієнтів буде відмінено</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
									</button>
									<button type="submit" form="removeSession" class="btn btn-primary">Save changes
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" form="removeSession" value="${session.getId()}" name="sessionId">
			</form>
		</c:when>
		<c:otherwise>
		<div class="text-center">
			<input form="buyTickets" class="w-50 btn btn-outline-success mx-auto" type="submit" value="order tickets">
		</div>
	</form>
	</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
