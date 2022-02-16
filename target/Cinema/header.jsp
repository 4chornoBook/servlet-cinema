<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 14.02.2022
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">Cinema</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-link active" href="#">Location</a>
			</div>
			<c:if test="${sessionScope.get('role').name().equals('ADMIN')}">
				<div class="navbar-nav">
					<a class="nav-link active" href="controller?action=newMovieForm">Add Movie</a>
					<a class="nav-link active" href="controller?action=newMovieSession">Add Session</a>
				</div>
			</c:if>
		</div>
		<c:choose>
			<c:when test="${sessionScope.user == null}">
				<div class="collapse navbar-collapse justify-content-end" id="3navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link active" href="registration.jsp">Register</a>
						<a class="nav-link active" href="login.jsp">Login</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="collapse navbar-collapse justify-content-end" id="3navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link active" href="controller?action=showProfile">
							<c:out value="${sessionScope.get('user').getName()}"/>
							<c:out value="${sessionScope.get('user').getSurname()}"/>
						</a>
						<a class="nav-link active" href="controller?action=logout">Logout</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</nav>