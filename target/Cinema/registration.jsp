<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 03.02.2022
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<head>
	<title>registration</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="controller?action=registration" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2>Registration</h2>
	<div class="mb-3">
		<label for="exampleInputEmail1" class="form-label">Login</label>
		<input type="text" name="login" minlength="5" maxlength="50" class="form-control" id="exampleInputEmail1"
			   aria-describedby="emailHelp">
		<div id="emailHelp" class="form-text">unique at least 5 symbols</div>
	</div>
	<div class="mb-3">
		<label for="exampleName" class="form-label">Your name</label>
		<input type="text" name="name" minlength="1" maxlength="300" class="form-control" id="exampleName"
			   aria-describedby="emailHelp">
		<div id="name" class="form-text">at least 1 symbol</div>
	</div>
	<div class="mb-3">
		<label for="exampleSurname" class="form-label">Your surname</label>
		<input type="text" name="surname" minlength="1" maxlength="300" class="form-control" id="exampleSurname"
			   aria-describedby="emailHelp">
		<div id="surname" class="form-text">at least 1 symbol</div>
	</div>
	<div class="mb-3">
		<label for="exampleInputPassword1" class="form-label">Password</label>
		<input type="password" name="password" class="form-control" id="exampleInputPassword1">
		<div id="passwordHelp" class="form-text">at least 5 symbols</div>
	</div>

	<button type="submit" class="btn btn-primary">Register</button>
</form>
</body>
</html>
