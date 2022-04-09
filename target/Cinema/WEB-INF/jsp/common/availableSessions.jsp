<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 12.02.2022
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tags/head.jspf" %>
<h2 style="margin-left: 5%; margin-top: 2%"><fmt:message key="sessions.schedule"/></h2>
<div style="display: flex; border: 2px solid darkblue; border-radius: 15px; background-color:  #f0f8ff;	margin: 0 5%">
	<div style="width: 65%; margin-left: 5%; padding: 10px; ">
		<h3><fmt:message key="sessions.sorting"/></h3>
		<form id="filterAndSorting" action="controller?action=sessionsSorting">
			<input type="hidden" name="action" value="sessionsSorting">
			<div class="row">
				<div class="col">
					<label for="dateSort" class="form-label"><fmt:message key="sessions.filter.date"/></label>
					<select id="dateSort" name="dateSort" class="form-select" aria-label="Default select example">
						<option value="" ${sessionScope.byDateNone}><fmt:message key="sessions.filter.choose"/></option>
						<option value="ascending" ${sessionScope.byDateAscending}><fmt:message
								key="sessions.filter.ascending"/></option>
						<option value="descending" ${sessionScope.byDateDescending}><fmt:message
								key="sessions.filter.descending"/></option>
					</select>
				</div>
				<div class="col">
					<label for="timeSort" class="form-label"><fmt:message key="sessions.filter.time"/></label>
					<select id="timeSort" name="timeSort" class="form-select" aria-label="Default select example">
						<option value="" ${sessionScope.byTimeNone}><fmt:message key="sessions.filter.choose"/></option>
						<option value="ascending" ${sessionScope.byTimeAscending}><fmt:message
								key="sessions.filter.ascending"/></option>
						<option value="descending" ${sessionScope.byTimeDescending}><fmt:message
								key="sessions.filter.descending"/></option>
					</select>
				</div>
				<div class="col">
					<label for="ticketsSort" class="form-label"><fmt:message
							key="sessions.filter.available.tickets"/></label>
					<select id="ticketsSort" name="ticketsSort" class="form-select"
							aria-label="Default select example">
						<option value="" ${sessionScope.byTicketsNone}><fmt:message
								key="sessions.filter.choose"/></option>
						<option value="ascending"${sessionScope.byTicketsAscending}><fmt:message
								key="sessions.filter.ascending"/></option>
						<option value="descending"${sessionScope.byTicketsDescending}><fmt:message
								key="sessions.filter.descending"/></option>
					</select>
				</div>
				<div class="col">
					<label for="movieNameSort" class="form-label"><fmt:message
							key="sessions.filter.movie.name"/></label>
					<select id="movieNameSort" name="movieNameSort" class="form-select"
							aria-label="Default select example">
						<option value="" ${sessionScope.byNameNone}><fmt:message key="sessions.filter.choose"/></option>
						<option value="ascending"${sessionScope.byNameAscending}><fmt:message
								key="sessions.filter.ascending"/></option>
						<option value="descending"${sessionScope.byNameDescending}><fmt:message
								key="sessions.filter.descending"/></option>
					</select>
				</div>
			</div>
			<button type="submit" style="margin-top: 30px" id="submitSorting" class="btn btn-primary mb-3"><fmt:message
					key="sessions.sort"/></button>
		</form>
	</div>
	<div style="background-color:  #f0f8ff;
	/*border: 2px darkblue solid; border-radius: 15px;*/
	 width: 20%; height: 25%; margin-left: 5%; padding: 15px; ">
		<h3><fmt:message key="sessions.filter"/></h3>
		<select name="movieFilter" class="form-select"
				form="filterAndSorting"
				aria-describedby="movieSelectionError"
				size="4" aria-label="Default select example">
			<c:forEach items="${sessionScope.availableMovies}" var="movie">
				<option value="<c:out value="${movie.getName()}"/>"><c:out value="${movie.getName()}"/></option>
			</c:forEach>
		</select>
		<button style="margin-top: 10px" form="filterAndSorting" type="submit" id="userFilter"
				class="btn btn-primary mb-3"><fmt:message key="sessions.filter.button"/></button>
	</div>
</div>
<div>
	<c:forEach items="${sessionScope['availableSessions']}" var="session">
		<div class="movie" style="
					border: 2px darkblue solid; display: flex;
	 				align-items: center;
					background-color: #f0f8ff;
					margin: 2% 5%;
					padding: 15px;
					border-radius: 15px;
					">
			<div>
				<img src="${session.getMovie().getImageURL()}"
					 style="height: 50vh; width:16.6vw; object-fit: cover;"
					 alt="${session.getMovie().getName()} image">
			</div>
			<div style="font-size: larger; padding-left: 10%">
				<h2><c:out value="${session.getMovie().getName()}"/></h2>
				<p><fmt:message key="sessions.session.date"/> <c:out value="${session.getMovieDate()}"/></p>
				<p><fmt:message key="sessions.session.time"/> <c:out value="${session.getBeginningTime()}"/> - <c:out
						value="${session.getEndingTime()}"/></p>
				<p><fmt:message key="sessions.session.ticket.price"/> <c:out
						value="${session.getTicketPrice()}"/> <fmt:message
						key="sessions.session.currency"/></p>
				<p><fmt:message key="sessions.session.length"/> <c:out
						value="${session.getMovie().getLengthInMinutes()}"/> <fmt:message
						key="sessions.session.time.unit"/></p>
				<form action="controller?action=showMovieSession" method="post">
					<input type="hidden" value="${session.getId()}" name="sessionId">
					<c:choose>
						<c:when test="${sessionScope.get('role').name().equals('ADMIN')}">
							<button class="btn btn-primary btn-lg" type="submit" value="details"><fmt:message
									key="sessions.session.details"/></button>
							<%--							<input class="btn btn-primary btn-lg" type="submit" value="details">--%>
						</c:when>
						<c:otherwise>
							<button class="btn btn-primary btn-lg" type="submit" value="buy ticket"><fmt:message
									key="sessions.session.buy.tickets"/></button>
							<%--							<input class="btn btn-primary btn-lg" type="submit" value="buy ticket">--%>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</c:forEach>
</div>
</div>
<form action="controller?action=sessionsPagination" style="margin-top: 20px">
	<nav aria-label="...">
		<ul class="pagination justify-content-center">
			<li class="page-item <c:out value="${currentPage == 1 ? 'disabled' : ''}"/>">
				<a class="page-link" href="controller?action=pagination&moveTo=prevPage" tabindex="-1"
				   aria-disabled="true"><fmt:message key="sessions.pagination.previous.page"/></a>
			</li>
			<c:forEach var="i" begin="1" end="${sessionScope.numberOfPages}">
				<c:choose>
					<c:when test="${sessionScope.currentPage == i}">
						<li class="page-item disabled" aria-current="page">
							<a class="page-link" href="controller?action=pagination&page=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<c:if test="${(i == currentPage-1 || i==currentPage+1) || (currentPage == numberOfPages && i >= currentPage - 2) || (currentPage == 1 && i <= currentPage + 2)}">
							<li class="page-item">
								<a class="page-link" href="controller?action=pagination&page=${i}">${i}</a>
							</li>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="page-item <c:out value="${currentPage == numberOfPages ? 'disabled' : ''}"/>">
				<a class="page-link" href="controller?action=pagination&moveTo=nextPage"><fmt:message
						key="sessions.pagination.next.page"/></a>
			</li>
		</ul>
	</nav>
</form>
