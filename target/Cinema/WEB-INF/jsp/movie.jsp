<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 12.02.2022
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/head.jspf" %>
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
		<img src="${sessionScope.session.getMovie().getImageURL()}"
			 style="height: 60vh; width:20vw; object-fit: cover;"
			 alt="${sessionScope.session.getMovie().getName()} image">
	</div>
	<div style="font-size: larger; padding-left: 10%">
		<h2><c:out value="${sessionScope.session.getMovie().getName()}"/></h2>
		<p><fmt:message key="movie.session.date"/> <c:out value="${sessionScope.session.getMovieDate()}"/></p>
		<p><fmt:message key="movie.session.time"/> <c:out value="${sessionScope.session.getBeginningTime()}"/></p>
		<p><fmt:message key="movie.session.ticket.price"/> <c:out value="${sessionScope.session.getMovie().getTicketPrice()}"/> <fmt:message key="movie.session.currency"/></p>
		<p><fmt:message key="movie.session.length"/> <c:out value="${sessionScope.session.getMovie().getLengthInMinutes()}"/> <fmt:message key="movie.session.time.unit"/></p>
		<p><fmt:message key="movie.session.genre"/>
			<%--				todo show genres with delimeter comma (create toString method for genre and--%>
			<c:forEach items="${sessionScope.session.getMovie().getGenres()}" var="genre">
				<c:out value="${genre.getName()}"/>;
			</c:forEach>
		</p>
		<p>
			<c:out value="${session.getMovie().getDescription()}"/>
		</p>
	</div>
</div>
<div>
	<h2 class="fs-2 text-center"><fmt:message key="movie.screen"/></h2>
	<c:if test="${!sessionScope.get('role').name().equals('ADMIN')}">
	<form action="controller?action=submitOrder" id="buyTickets" method="post">
		<input type="hidden" form="buyTickets" value="${session.getId()}" name="sessionId">
		</c:if>
		<table style="margin: 0 auto" class="${requestScope.noTicketsError}" aria-describedby="noTicketsError">
			<c:forEach var="i" begin="1" end="100">
				<c:if test="${(i-1) % 10 == 0}">
					<tr>
				</c:if>
				<td>
					<c:choose>
						<c:when test="${ticketsNumber.contains(i)}">
							<input type="checkbox" name="numberPlace" disabled class="btn-check" id="btn-check${i}"
								   autocomplete="off"/>
							<label class="btn btn-secondary" for="btn-check${i}"><c:out value="${i}"/><br><fmt:message key="movie.place"/></label>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="numberPlace" value="${i}" class="btn-check" id="btn-check${i}"
								   autocomplete="off"/>
							<label class="btn btn-outline-primary" for="btn-check${i}"><c:out
									value="${i}"/><br><fmt:message key="movie.place"/></label>
						</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${(i-1) % 10 == 9}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<div id="noTicketsError" class="invalid-feedback text-center fs-3" style="margin: 0 auto">
			<fmt:message key="movie.tickets.error"/>
		</div>
		<c:choose>
		<c:when test="${sessionScope.get('role').name().equals('ADMIN')}">
			<form action="controller?action=removeMovieSession" id="removeSession" method="post">
				<div class="text-center">
					<button type="button" form="removeSession" class="w-50 btn btn-outline-danger"
							data-bs-toggle="modal"
							data-bs-target="#myModal">
						<fmt:message key="movie.delete.session"/>
					</button>
					<div class="modal" id="myModal" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"><fmt:message key="movie.modal.title"/></h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<p><fmt:message key="movie.modal.message"/></p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="movie.modal.close"/>
									</button>
									<button type="submit" form="removeSession" class="btn btn-primary"><fmt:message key="movie.modal.delete"/>
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
			<button form="buyTickets" class="w-50 btn btn-outline-success mx-auto" type="submit" value="order tickets">
				<fmt:message key="movie.order.tickets"/>
			</button>
				<%--			<input form="buyTickets" class="w-50 btn btn-outline-success mx-auto" type="submit" value="order tickets">--%>
		</div>
	</form>
	</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
