<%--
  Created by IntelliJ IDEA.
  User: samuel
  Date: 07/12/22
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Project</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<c:if test="${addedProjectSuccess}">
    <div>Successfully added Project: ${addedProject.name}</div>
</c:if>

<c:url var="add_project_url" value="/project/add"/>
<form:form action="${add_project_url}" method="post" modelAttribute="project" class="row m-12" style="width: 90%; margin-left: auto; margin-right: auto; margin-top: 20px">
    <div class="col-md-6">
        <form:label path="name" class="form-label">Project name: </form:label>
        <form:input type="text" path="name" class="form-control" required="required"/>
    </div>
    <div class="col-md-2">
        <form:label path="startDate" class="form-label">Start date: </form:label>
        <form:input type="date" path="startDate" class="form-control"/>
    </div>
    <div class="col-md-2">
        <form:label path="expectedEndDate" class="form-label">Expected end date: </form:label>
        <form:input type="date" path="expectedEndDate" class="form-control"/>
    </div>
    <div class="col-md-2">
        <form:label path="endDate" class="form-label">End date: </form:label>
        <form:input type="date" path="endDate" class="form-control"/>
    </div>
    <div class="col-md-2">
        <form:label path="budget" class="form-label">Budget: </form:label>
        <form:input type="number" path="budget" class="form-control" step="any"/>
    </div>
    <div class="col-12">
        <form:label path="description" class="form-label">Description: </form:label>
        <form:input type="text" path="description" class="form-control"/>
    </div>
    <div class="col-md-2">
        <form:label path="status" class="form-label">Status:
            <select name="status" class="form-control">
                <option value="IN_ANALYSIS">Em análise</option>
                <option value="ANALYSIS_DONE">Análise realizada</option>
                <option value="APPROVED">Análise aprovada</option>
                <option value="ANALYSIS_STARTED">Iniciado</option>
                <option value="PLANNED">Planejado</option>
                <option value="RUNNING">Em andamento</option>
                <option value="FINISHED">Encerrado</option>
                <option value="CANCELED">Cancelado</option>
            </select>
        </form:label>
    </div>
    <div class="col-md-2">
        <form:label path="risk" class="form-label">Risk:
            <select name="risk" class="form-control">
                <option value="LOW">Baixo risco</option>
                <option value="MEDIUM">Médio risco</option>
                <option value="HIGH">Alto risco</option>
            </select>
        </form:label>
    </div>
    <div class="col-md-6">
        <form:label path="manager" class="form-label">
            Select a Manager:
            <select name="manager" class="form-control" required="required">
                <c:forEach items="${employees}" var="manager">
                    <option value="${manager.id}">${manager.name}</option>
                </c:forEach>
            </select>
        </form:label>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end" style="margin-top: 10px">
        <input type="submit" value="submit" class="btn btn-primary btn"/>
    </div>
</form:form>
</body>
</html>
