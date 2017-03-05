<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css" />

</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <div align="center">
        <h2>Пожалуйста, авторизируйтесь</h2>
            </div>
        <div class="label">Логин</div>
        <input type="text" class="form-control" name="j_username" placeholder="Email address" required="">
        <div class="label">Пароль</div>
        <input type="password" class="form-control" name="j_password" placeholder="Password" required="">
        <p></p>
        <input class="btn btn-primary btn-block" type="submit" value="Войти"/>
    </form>
</div>
<div class="error">
    ${error}
</div>
</body>
</html>
