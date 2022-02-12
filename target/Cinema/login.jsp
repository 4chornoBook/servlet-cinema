<%--
  Created by IntelliJ IDEA.
  User: kosti
  Date: 02.02.2022
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
</head>
<style>
</style>
<body>
<h1>Super cinema</h1>
<form action="controller?action=login" method="post">
    <label for="login"></label>
    <input type="text" name="login" id="login" placeholder="login">
    <br>
    <br>
    <label for="password"></label>
    <input type="password" name="password" id="password" minlength="5" maxlength="128" placeholder="password"
    <%--           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" --%>
           title="enter normal password"
    >
    <br>
    <input type="submit" name="submit" value="Login">
</form>
</body>
</html>
