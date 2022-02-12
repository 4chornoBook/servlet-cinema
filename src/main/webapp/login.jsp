<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 02.02.2022
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/head.jsp"/>
<style>
    html,
    body {
        height: 100%;
    }

    body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
    }

    .form-signin .checkbox {
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
<%--<h1>Super cinema</h1>--%>
<form action="controller?action=login" method="post">
	<%--	<label for="login"></label>--%>
	<%--	<input type="text" name="login" id="login" placeholder="login">--%>
	<%--	<br>--%>
	<%--	<br>--%>
	<%--	<label for="password"></label>--%>
	<%--	<input type="password" name="password" id="password" minlength="5" maxlength="128" placeholder="password"--%>
	<%--	&lt;%&ndash;           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" &ndash;%&gt;--%>
	<%--		   title="enter normal password"--%>
</form>
<main class="form-signin">
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
</main>
</body>
</html>
