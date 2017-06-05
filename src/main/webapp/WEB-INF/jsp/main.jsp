<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css" />
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<div align="right">
<security:authorize access="isAuthenticated()">
    <div class="inline">
        Добро пожаловать, <security:authentication property="principal.username" />
    </div>
    <div class="inline">
        <form action="${pageContext.request.contextPath}/j_spring_security_logout" method="post" id="logoutForm">
            <input type = "submit" value = "Выйти"/>
        </form>
    </div>
</security:authorize>
    </div>
<div class="container">
    <div class="panel-group" id="accordion">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Таблицы</a>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                    <form:form  method = "get" action = "${pageContext.request.contextPath}/tables">
                        <ul>
                    <c:forEach items="${allTables}" var="item">
                            <li>
                        <input type="radio"  id="radio" name="table" value="${item}"/>
                        <label for="radio">${item}</label>
                                </li>
                        </c:forEach>
                        </ul>
                        <input type="submit" class="btn btn-info" value="Выбрать"/>
                    </form:form>
                    </div>
                </div>

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Запросы</a>
                    </h4>
                </div>
                <div id="collapse2" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="list-group">
                            <a href="/request" class="list-group-item">
                                <h4 class="list-group-item-heading">Статистика по причинам вызова в период</h4>
                                <p class="list-group-item-text"> Параметры: дата начала, дата окончания.</p>
                            </a>
                            <a href="/request" class="list-group-item">
                                <h4 class="list-group-item-heading">Статистика по используемой технике в регионе</h4>
                                <p class="list-group-item-text">Параметры: дата начала, дата окончания, регион</p>
                            </a>
                            <a href="/request" class="list-group-item">
                                <h4 class="list-group-item-heading">Статистика причин происшествий в регионе в период</h4>
                                <p class="list-group-item-text">Параметры: дата начала, дата окончания, регион</p>
                            </a>
                            <a href="/request" class="list-group-item">
                                <h4 class="list-group-item-heading">Статистика происшествий по регионе в период</h4>
                                <p class="list-group-item-text">Параметры: дата начала, дата окончания</p>
                            </a>
                            <a href="/request" class="list-group-item">
                                <h4 class="list-group-item-heading">Статистика происшествий по причине вызовов в период</h4>
                                <p class="list-group-item-text">Параметры: дата начала, дата окончания, причина</p>
                            </a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
    </div>
</body>
</html>
