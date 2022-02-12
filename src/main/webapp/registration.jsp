<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 03.02.2022
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<h2>Simple registration</h2>
<form action="controller?action=registration" method="post">
    <p>login</p>
    <input type="text" name="login" minlength="5" maxlength="50" placeholder="login">
    <br>
    <p>Your name</p>
    <input type="text" name="name" minlength="1" maxlength="300" placeholder="name">
    <br>
    <p>Your surname</p>
    <input type="text" name="surname" minlength="1" maxlength="300" placeholder="surname">
    <br>
    <p>password</p>
    <input type="password" name="password" minlength="5" maxlength="50" placeholder="password">
    <br>
    <input type="submit" value="register">
</form>
</body>
</html>
