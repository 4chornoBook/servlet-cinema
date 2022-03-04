<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 02.02.2022
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ include file="/WEB-INF/tags/head.jspf" %>
<style>
    html,
    body {
        height: 100%;
    }

    body {
        align-items: center;
        background-color: #f5f5f5;
    }

    .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
        margin-top: 10%;
    }

    .form-signin.checkbox {
        font-weight: 400;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body>
<jsp:include page="WEB-INF/common_elements/header.jsp"/>
<div class="form-signin">
	<form action="controller?action=login" method="post">
		<h1 class="h3 mb-3 fw-normal"><fmt:message key="login.title"/></h1>
		<div class="form-floating">
			<input type="text" name="login" minlength="5" maxlength="50"
				   class="form-control ${requestScope.userLoginError}" aria-describedby="userDataValidation"
				   id="floatingInput" placeholder="name@example.com" required>
			<label for="floatingInput"><fmt:message key="login.login"/></label>
		</div>
		<div class="form-floating">
			<input type="password" name="password" minlength="5" maxlength="50"
				   class="form-control ${requestScope.userLoginError}" id="floatingPassword" placeholder="Password"
				   required>
			<label for="floatingPassword"><fmt:message key="login.password"/></label>
			<div id="userDataValidation" class="invalid-feedback">
				<fmt:message key="login.authorization.error"/>
			</div>
		</div>
		<button class="w-100 btn btn-lg btn-primary" type="submit"><fmt:message key="login.login.button"/></button>
	</form>
</div>
<jsp:include page="/WEB-INF/common_elements/footer.jsp"/>
</body>
</html>
