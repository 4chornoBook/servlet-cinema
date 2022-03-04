<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 03.02.2022
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ include file="/WEB-INF/tags/head.jspf"%>
<head>
	<title>registration</title>
</head>
<body>
<jsp:include page="WEB-INF/common_elements/header.jsp"/>
<form action="controller?action=registration" method="post"
	  style="
	width: 100%;
	max-width: 500px;
	margin: auto;
	margin-top: 5%;">
	<h2><fmt:message key="registration.title"/></h2>
	<div class="mb-3">
		<label for="userLogin" class="form-label"><fmt:message key="registration.login"/></label>
		<input type="text" name="login" minlength="5" maxlength="50" class="form-control ${requestScope.loginError}"
			   id="userLogin" aria-describedby="userLoginError" aria-describedby="notUniqueLoginError" required>
		<div id="emailHelp" class="form-text"><fmt:message key="registration.login.comment"/></div>
		<div id="userLoginError" class="invalid-feedback">
			<fmt:message key="registration.login.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="userName" class="form-label"><fmt:message key="registration.name"/></label>
		<input type="text" name="name" minlength="1" maxlength="300" class="form-control ${requestScope.nameError}"
			   id="userName" aria-describedby="userNameError" required>
		<div id="userNameError" class="invalid-feedback">
			<fmt:message key="registration.name.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="userSurname" class="form-label"><fmt:message key="registration.surname"/> </label>
		<input type="text" name="surname"
			   minlength="1" maxlength="300" class="form-control ${requestScope.surnameError}" id="userSurname"
			   aria-describedby="userSurnameError" required>
		<div id="userSurnameError" class="invalid-feedback">
			<fmt:message key="registration.surname.error"/>
		</div>
	</div>
	<div class="mb-3">
		<label for="userPassword" class="form-label"><fmt:message key="registration.password"/></label>
		<input type="password" name="password" minlength="5" maxlength="64" class="form-control ${requestScope.passwordError}" id="userPassword"
			   aria-describedby="userPasswordError" required>
		<div id="passwordHelp" class="form-text"><fmt:message key="registration.password.comment"/></div>
		<div id="userPasswordError" class="invalid-feedback">
			<fmt:message key="registration.password.error"/>
		</div>
	</div>
	<button type="submit" class="btn btn-primary"><fmt:message key="registration.register"/></button>
</form>
</body>
</html>
