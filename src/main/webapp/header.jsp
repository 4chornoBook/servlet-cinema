<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 14.02.2022
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous"></script>
</head>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">Cinema</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
							data-bs-toggle="dropdown" aria-expanded="false">
						<fmt:message key="header.menu.language"/>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<form action="controller?action=lang" method="post">
							<button class="dropdown-item" name="en" value="English" type="submit"><fmt:message
									key="header.menu.language.eng"/></button>
							<button class="dropdown-item" name="uk" value="Ukrainian" type="submit"><fmt:message
									key="header.menu.language.ukr"/></button>
						</form>
					</ul>
				</div>
			</div>
			<c:if test="${sessionScope.get('role').name().equals('ADMIN')}">
				<div class="navbar-nav">
					<a class="nav-link active" href="controller?action=newMovieForm"><fmt:message
							key="header.menu.new.movie"/></a>
					<a class="nav-link active" href="controller?action=newMovieSession"><fmt:message
							key="header.menu.new.session"/></a>
				</div>
			</c:if>
		</div>
		<c:choose>
			<c:when test="${sessionScope.user == null}">
				<div class="collapse navbar-collapse justify-content-end" id="3navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link active" href="registration.jsp"><fmt:message key="header.menu.register"/></a>
						<a class="nav-link active" href="login.jsp"><fmt:message key="header.menu.login"/></a>
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
						<a class="nav-link active" href="controller?action=logout"><fmt:message
								key="header.menu.log.out"/></a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</nav>