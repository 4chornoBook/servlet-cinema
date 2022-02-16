<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 02.02.2022
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<style>
    html,
    body {
        height: 100%;
    }

    body {
        /*display: flex;*/
        align-items: center;
        /*padding-top: 40px;*/
        /*padding-bottom: 40px;*/
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
<jsp:include page="header.jsp"/>
<div class="form-signin">
	<form action="controller?action=login" method="post">
		<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		<div class="form-floating">
			<input type="text" name="login" class="form-control" id="floatingInput" placeholder="name@example.com">
			<label for="floatingInput">Login</label>
		</div>
		<div class="form-floating">
			<input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
			<label for="floatingPassword">Password</label>
		</div>
		<%--		<div class="checkbox mb-3">--%>
		<%--			<label>--%>
		<%--				<input type="checkbox" value="remember-me"> Remember me--%>
		<%--			</label>--%>
		<%--		</div>--%>
		<button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
	</form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
