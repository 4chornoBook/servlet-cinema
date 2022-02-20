<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 12.02.2022
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2 style="margin-left: 5%; margin-top: 2%">Розклад показів</h2>
<div style="display: flex; border: 2px solid darkblue; border-radius: 15px; background-color:  #f0f8ff;	margin: 0 5%">
	<div style="width: 65%; margin-left: 5%; padding: 10px; ">
		<h3>Sorting</h3>
		<form id="filterAndSorting" action="controller?action=sessionsSorting">
			<input type="hidden" name="action" value="sessionsSorting">
			<div class="row">
				<div class="col">
					<label for="dateSort" class="form-label">Date</label>
					<select id="dateSort" name="dateSort" class="form-select" aria-label="Default select example">
						<option value="" ${sessionScope.byDateNone}>Choose</option>
						<option value="ascending" ${sessionScope.byDateAscending}>За зростанням</option>
						<option value="descending" ${sessionScope.byDateDescending}>За спаданням</option>
					</select>
				</div>
				<div class="col">
					<label for="timeSort" class="form-label">Time</label>
					<select id="timeSort" name="timeSort" class="form-select" aria-label="Default select example">
						<option value="" ${sessionScope.byTimeNone}>Choose</option>
						<option value="ascending" ${sessionScope.byTimeAscending}>За зростанням</option>
						<option value="descending" ${sessionScope.byTimeDescending}>За спаданням</option>
					</select>
				</div>
				<div class="col">
					<label for="ticketsSort" class="form-label">Доступні квитки</label>
					<select id="ticketsSort" name="ticketsSort" class="form-select"
							aria-label="Default select example">
						<option value="" ${sessionScope.byTicketsNone}>Choose</option>
						<option value="ascending"${sessionScope.byTicketsAscending}>За зростанням</option>
						<option value="descending"${sessionScope.byTicketsDescending}>За спаданням</option>
					</select>
				</div>
				<div class="col">
					<label for="movieNameSort" class="form-label">За назвою фільму</label>
					<select id="movieNameSort" name="movieNameSort" class="form-select"
							aria-label="Default select example">
						<option value="" ${sessionScope.byNameNone}>Choose</option>
						<option value="ascending"${sessionScope.byNameAscending}>За зростанням</option>
						<option value="descending"${sessionScope.byNameDescending}>За спаданням</option>
					</select>
				</div>
<%--				<label for="submitSorting" class="form-label">Сортувати</label>--%>
			</div>
				<button type="submit" style="margin-top: 30px" id="submitSorting" class="btn btn-primary mb-3">Сортувати</button>
		</form>
	</div>
	<div style="background-color:  #f0f8ff;
	/*border: 2px darkblue solid; border-radius: 15px;*/
	 width: 20%; height: 25%; margin-left: 5%; padding: 15px; ">
		<h3>Filter</h3>
		<select name="movieFilter" class="form-select"
				form="filterAndSorting"
				aria-describedby="movieSelectionError"
				size="4" aria-label="Default select example">
			<c:forEach items="${sessionScope.availableMovies}" var="movie">
				<option value="<c:out value="${movie.getName()}"/>"><c:out value="${movie.getName()}"/></option>
			</c:forEach>
		</select>
		<button style="margin-top: 10px" form="filterAndSorting" type="submit" id="userFilter" class="btn btn-primary mb-3">Фільтр</button>
	</div>
</div>
<div>
	<c:forEach items="${sessionScope['availableSessions']}" var="session">
		<div class="movie" style="
					border: 2px darkblue solid; display: flex;
	 				align-items: center;
					background-color: #f0f8ff;
					/*margin-left: 5%;*/
					margin: 2% 5%;
					/*margin-top: 3%;*/
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
				<p>Дата показу: <c:out value="${session.getMovieDate()}"/></p>
				<p>Час показу: <c:out value="${session.getBeginningTime()}"/> - <c:out
						value="${session.getEndingTime()}"/></p>
				<p>Ціна квитка:<c:out value="${session.getMovie().getTicketPrice()}"/>грн.</p>
				<p>Тривалість: <c:out value="${session.getMovie().getLengthInMinutes()}"/> хв.</p>
				<form action="controller?action=showMovieSession" method="post">
					<input type="hidden" value="${session.getId()}" name="sessionId">
					<c:choose>
						<c:when test="${sessionScope.get('role').name().equals('ADMIN')}">
							<input class="btn btn-primary btn-lg" type="submit" value="details">
						</c:when>
						<c:otherwise>
							<input class="btn btn-primary btn-lg" type="submit" value="buy ticket">
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
			<li class="page-item">
				<a class="page-link" href="controller?action=pagination&moveTo=prevPage" tabindex="-1"
				   aria-disabled="true">Previous</a>
			</li>
			<c:forEach var="i" begin="1" end="${sessionScope.numberOfPages}">
				<c:choose>
					<c:when test="${sessionScope.currentPage == i}">
						<li class="page-item disabled" aria-current="page">
							<a class="page-link" href="controller?action=pagination&page=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="controller?action=pagination&page=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="page-item">
				<a class="page-link" href="controller?action=pagination&moveTo=nextPage">Next</a>
			</li>
		</ul>
	</nav>
</form>
