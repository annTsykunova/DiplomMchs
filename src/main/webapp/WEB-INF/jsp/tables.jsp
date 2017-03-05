<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="secuity" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"
            integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js"
            integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
            integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK"
            crossorigin="anonymous"></script>
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
        <c:if test="${not empty allRegions}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Dictionary Regions</a>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">

                    <table class="table table-hover " >
                        <thead>
                        <tr>
                            <th>id_region</th>
                            <th>region_code</th>
                            <th>region_name</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${allRegions}" var="currentRegion">
                        <tr>
                            <form:form action = "${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="region">
                            <td><form:input path="regionId" value="${currentRegion.regionId}" readonly="readonly"/></td>
                            <td><form:input class="form-control" path="regionCode" value="${currentRegion.regionCode}"/></td>
                            <td><form:input class="form-control" path="regionName" value="${currentRegion.regionName}"/></td>
                            <td>
                                <input type="submit" class="btn btn-success" value="Save" name="save"
                                       onclick="return confirm('Are you sure?')"></td>
                            </form:form>
                            <td>
                                <form:form action = "${pageContext.request.contextPath}/tables/edit/delete" method = "post" >
                                    <input type="hidden" name="table" class = "input-field" value = "dictionary_regions"/>
                                    <input type="hidden" name="id"  class = "input-field"  value = "${currentRegion.regionId}"/>
                                    <input type="submit" class="btn btn-danger" value="Удалить"
                                           onclick="return confirm('Are you sure?')">
                                </form:form>
                               </td>
                        </tr>
                        </c:forEach>


                </div>
            </div>

            <a class="btn btn-lg btn-success" href="#" data-toggle="modal" data-target="#basicModal1">Добавить</a>
            <div class="modal fade" id="basicModal1" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                            <h4 class="modal-title">Добавить регион</h4>
                        </div>
                        <form:form action="${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="callReason">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="regionCode">Код региона</label>
                                    <form:input class="form-control" id="regionCode" type="number" path="regionCode"
                                           required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="regionName">Регион</label>
                                    <form:input class="form-control" id="regionName" type="text" path="regionName" required="true"/>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-default" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
        <c:if test="${not empty allVechicleType}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Vechicle Type</a>
                </h4>
            </div>
            <div id="collapse2" class="panel-collapse collapse in">
                <div class="panel-body">
                    <table class="table table-hover ">
                        <thead>
                        <tr>
                            <th>id_version</th>
                            <th>version</th>
                            <th>nameType</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${allVechicleType}" var="currentType">
                        <tr>
                            <form:form action = "${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="vechicleType">
                            <td><form:input path="versionId" value="${currentType.versionId}" readonly="readonly"/></td>
                            <td><form:input class="form-control" path="version" value="${currentType.version}"/></td>
                            <td><form:input class="form-control" path="nameType" value="${currentType.nameType}"/></td>
                            <td>
                                <input type="submit" class="btn btn-success" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')"></td>
                            </form:form>
                            <td>
                                <form:form action = "${pageContext.request.contextPath}/tables/edit/delete" method = "post" >
                                <input type="hidden" name="table" class = "input-field" value = "vechicle_type"/>
                                <input type="hidden" name="id"  class = "input-field"  value = "${currentType.versionId}"/>
                                <input type="submit" class="btn btn-danger" value="Удалить"
                                       onclick="return confirm('Are you sure?')">
                                </form:form>
                            </td>
                        </tr>
                        </c:forEach>

                </div>
            </div>
            <a class="btn btn-lg btn-success" href="#" data-toggle="modal" data-target="#basicModal2">Добавить</a>
            <div class="modal fade" id="basicModal2" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                            <h4 class="modal-title">Добавить тип транспортного средства</h4>
                        </div>
                        <form:form action="${pageContext.request.contextPath}/tables/edit" method="post"  modelAttribute="vechicleType">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="version">Версия</label>
                                    <form:input class="form-control" id="version" type="number" path="version"
                                                required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="nameType">Название</label>
                                    <form:input class="form-control" id="nameType" type="text" path="nameType" required="true"/>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-default" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
        <c:if test="${not empty allVechicleClass}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Vechicle Class</a>
                </h4>
            </div>
            <div id="collapse3" class="panel-collapse collapse in">
                <div class="panel-body">

                    <table class="table table-hover " id='table_element'>
                        <thead>
                        <tr>
                            <th>id_version</th>
                            <th>version</th>
                            <th>name</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${allVechicleClass}" var="currentClass">
                        <tr>
                            <form:form action = "${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="vechicleClass">
                            <td><form:input path="classId" value="${currentClass.classId}" readonly="readonly"/></td>
                            <td><form:input class="form-control" path="version" value="${currentClass.version}"/></td>
                            <td><form:input class="form-control" path="name" value="${currentClass.name}"/></td>
                            <td><form:input class="form-control" path="description" value="${currentClass.description}"/>
                            </td>
                            <td><form:input class="form-control" path="vechicleId" value="${currentClass.vechicleId}"/></td>
                            <td>
                                <input type="submit" class="btn btn-success" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">
                            </td>
                            </form:form>

                            <td>
                                <form:form action = "${pageContext.request.contextPath}/tables/edit/delete" method = "post" >
                                <input type="hidden" name="table" class = "input-field" value = "vechicle_class"/>
                                <input type="hidden" name="id"  class = "input-field"  value = "${currentClass.classId}"/>
                                <input type="submit" class="btn btn-danger" value="Удалить"
                                       onclick="return confirm('Are you sure?')">
                                </form:form>
                            </td>
                        </tr>
                        </c:forEach>

                </div>
            </div>
            <a class="btn btn-lg btn-success" href="#" data-toggle="modal" data-target="#basicModal3">Добавить</a>
            <div class="modal fade" id="basicModal3" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                            <h4 class="modal-title">Добавить класс транспортного средства</h4>
                        </div>
                        <form:form action="${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="vechicleClass">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="version">Версия</label>
                                    <form:input class="form-control" id="version" type="number" path="version"
                                                required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="name">Название</label>
                                    <form:input class="form-control" id="name" type="text" path="name" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="description">Описание</label>
                                    <form:input class="form-control" id="description" type="text" path="description" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="vechicleId">Id типа</label>
                                    <form:input class="form-control" id="vechicleId" type="text" path="vechicleId" required="true"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-default" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
        <c:if test="${not empty allCallReason}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Call Reason</a>
                </h4>
            </div>
            <div id="collapse4" class="panel-collapse collapse in">
                <div class="panel-body">

                    <table class="table table-hover " >
                        <thead>
                        <tr>
                            <th>id_reason</th>
                            <th>version</th>
                            <th>nameReason</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${allCallReason}" var="currentReason">
                        <tr>
                            <form:form action = "${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="callReason">
                            <td><form:input path="reasonId" value="${currentReason.reasonId}" readonly="readonly"/></td>
                            <td><form:input class="form-control" path="version" value="${currentReason.version}"/></td>
                            <td><form:input class="form-control" path="nameReason" value="${currentReason.nameReason}"/></td>
                            <td>
                                <input type="submit" class="btn btn-success" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">

                            </td>
                            </form:form>
                            <td>
                                <form:form action = "${pageContext.request.contextPath}/tables/edit/delete" >
                                <input type="hidden" name="table" class = "input-field" value = "call_reason"/>
                                <input type="hidden" name="id"  class = "input-field"  value = "${currentReason.reasonId}"/>
                                <input type="submit" class="btn btn-danger" value="Удалить"
                                       onclick="return confirm('Are you sure?')">
                                </form:form>
                        </tr>
                        </c:forEach>

                </div>
            </div>
            <a class="btn btn-lg btn-success" href="#" data-toggle="modal" data-target="#basicModal">Добавить</a>
            <div class="modal fade" id="basicModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                            <h4 class="modal-title">Добавить причину вызова</h4>
                        </div>
                        <form:form action="${pageContext.request.contextPath}/tables/edit" method="post" modelAttribute="callReason">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="version">Версия</label>
                                    <form:input class="form-control" id="version" type="number" path="version"
                                                required="true"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="nameReason">Причина вызова</label>
                                    <form:input class="form-control" id="nameReason" type="text" path="nameReason" required="true"/>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-default" value="Сохранить" name="save"
                                       onclick="return confirm('Are you sure?')">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>
