<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css" />
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {

            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['Work',     11],
                ['Eat',      2],
                ['Commute',  2],
                ['Watch TV', 2],
                ['Sleep',    7]
            ]);

            var options = {
                title: 'My Daily Activities'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
        }

    </script>
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
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Выбор параметров запроса</h3>
        </div>
        <div class="panel-body">
<form:form  method = "post" action = "${pageContext.request.contextPath}/request/count">
            <label for="startdate">Дата начала</label>
            <input type="date" name="startdate" id="startdate" required="true"/>
            <label for="enddate">Дата окончания</label>
            <input type="date" name="enddate" id="enddate" required="true"/>

            <input type="submit" value="Найти"/>
</form:form>
        </div>
    </div>
<c:if test="${not empty callses.statisticCallses}">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">Result of request</h3>
            <div align="right">
                <form:form  method = "post" action = "${pageContext.request.contextPath}/request/export" modelAttribute="callses">


            </div>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover " >
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Регион</th>
                    <th>Транспортное средство</th>
                    <th>Причина</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <c:forEach items="${callses.statisticCallses}" var="currentCall" varStatus="vs">
                <tr>
                    <td><form:input type="hidden" path="statisticCallses[${vs.index}].date"/>${curCall.date}</td>
                    <td><form:input type="hidden" path="statisticCallses[${vs.index}].regionName"/>${curCall.regionName}</td>
                    <td><form:input type="hidden" path="statisticCallses[${vs.index}].vechicleName"/>${curCall.vechicleName}</td>
                    <td><form:input type="hidden" path="statisticCallses[${vs.index}].reasonName"/>${curCall.reasonName}</td>
                    <td><form:input type="hidden" path="statisticCallses[${vs.index}].count"/>${curCall.count}</td>

                </tr>
                </c:forEach>
                <input type="submit" value="Экспортировать" class="btn btn-default"/>
                </form:form>
        </div>
        </div>

    </c:if>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Выбор параметров запроса</h3>
    </div>
    <div class="panel-body">
        <form:form  method = "post" action = "${pageContext.request.contextPath}/region" modelAttribute="region">
            <label for="startdate">Дата начала</label>
            <input type="date" name="startdate" id="startdate" required="true"/>
            <label for="enddate">Дата окончания</label>
            <input type="date" name="enddate" id="enddate" required="true"/>
            <form:select path="regionId">
                <form:options itemLabel="regionName" itemValue="regionId" items="${allRegions}" />
            </form:select>
            <input type="submit" value="Найти"/>
        </form:form>
    </div>
</div>
<c:if test="${not empty calls.statisticCallses}">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">Result of request</h3>
            <div align="right">
                <form:form  method = "post" action = "${pageContext.request.contextPath}/request/export" modelAttribute="calls">

            </div>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover " >
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Регион</th>
                    <th>Транспортное средство</th>
                    <th>Причина</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <c:forEach items="${calls.statisticCallses}" var="curCall" varStatus="vs">
                    <tr>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].date"/>${curCall.date}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].regionName"/>${curCall.regionName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].vechicleName"/>${curCall.vechicleName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].reasonName"/>${curCall.reasonName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].count"/>${curCall.count}</td>

                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Экспортировать" class="btn btn-default "/>
            </form:form>

        </div>
    </div>

    </c:if>

<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Выбор параметров запроса</h3>
    </div>
    <div class="panel-body">
        <form:form  method = "post" action = "${pageContext.request.contextPath}/reason/region" modelAttribute="region">
            <label for="startdate">Дата начала</label>
            <input type="date" name="startdate" id="startdate" required="true"/>
            <label for="enddate">Дата окончания</label>
            <input type="date" name="enddate" id="enddate" required="true"/>
            <form:select path="regionId">
                <form:options itemLabel="regionName" itemValue="regionId" items="${allRegions}" />
            </form:select>
            <input type="submit" value="Найти"/>
        </form:form>
    </div>
</div>
<c:if test="${not empty callsReason.statisticCallses}">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">Result of request</h3>
            <div align="right">
                <form:form  method = "post" action = "${pageContext.request.contextPath}/request/export" modelAttribute="callsReason">

            </div>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover " >
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Регион</th>
                    <th>Транспортное средство</th>
                    <th>Причина</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <c:forEach items="${callsReason.statisticCallses}" var="curCall" varStatus="vs">
                    <tr>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].date"/>${curCall.date}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].regionName"/>${curCall.regionName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].vechicleName"/>${curCall.vechicleName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].reasonName"/>${curCall.reasonName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].count"/>${curCall.count}</td>

                    </tr>
                </c:forEach>


            </table>
            <input type="submit" value="Экспортировать" class="btn btn-default"/>
            </form:form>
        </div>
    </div>

</c:if>

<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Выбор параметров запроса</h3>
    </div>
    <div class="panel-body">
        <form:form  method = "post" action = "${pageContext.request.contextPath}/region/reason">
            <label for="startdate">Дата начала</label>
            <input type="date" name="startdate" id="startdate" required="true"/>
            <label for="enddate">Дата окончания</label>
            <input type="date" name="enddate" id="enddate" required="true"/>
            <input type="submit" value="Найти"/>
        </form:form>
    </div>
</div>
<c:if test="${not empty callsRegion.statisticCallses}">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">Result of request</h3>
            <div align="right">
                <form:form  method = "post" action = "${pageContext.request.contextPath}/request/export" modelAttribute="callsRegion">

            </div>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover " >
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Регион</th>
                    <th>Транспортное средство</th>
                    <th>Причина</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <c:forEach items="${callsRegion.statisticCallses}" var="curCall" varStatus="vs">
                    <tr>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].date"/>${curCall.date}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].regionName"/>${curCall.regionName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].vechicleName"/>${curCall.vechicleName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].reasonName"/>${curCall.reasonName}</td>
                        <td><form:input type="hidden" path="statisticCallses[${vs.index}].count"/>${curCall.count}</td>

                    </tr>
                </c:forEach>


            </table>
            <input type="submit" value="Экспортировать" class="btn btn-default"/>
            </form:form>
        </div>
    </div>

</c:if>

<input type="submit" onclick="drawChart()" value="Диаграмма по количеству транспорта в области"/>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
<input type="submit" onclick="drawChart()" value="Диаграмма по количеству причин в областях Беларуси"/>


</body>
</html>
