<%--
  Created by IntelliJ IDEA.
  User: samuel
  Date: 08/12/22
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
</head>
<body>
<jsp:include page="index.jsp" />
<c:url var="edit_project_url" value="/project/edit/${project.id}"/>
<form:form action="${edit_project_url}" method="post" modelAttribute="project" class="row m-12" style="width: 90%; margin-left: auto; margin-right: auto">
    <div class="col-md-6">
        <form:label path="name" for="inputProjectName" class="form-label">Project Name</form:label>
        <form:input path="name" type="text" class="form-control" id="inputProjectName" placeholder="${project.name}" />
    </div>
    <div class="col-md-6">
        <form:label path="manager" for="inputManager" class="form-label">Manager
        <select name="manager" class="form-control" style="margin-top: 8px; width: 200px">
            <option value="${project.manager.id}">${project.manager.name}</option>
            <c:forEach items="${employees}" var="manager">
                <c:if test="${project.manager.id != manager.id}">
                    <option value="${manager.id}">${manager.name}</option>
                </c:if>
            </c:forEach>
        </select>
        </form:label>
    </div>
    <div class="col-12">
        <form:label path="description" for="inputDescription" class="form-label">Description</form:label>
        <form:input path="description" type="text" class="form-control" id="inputDescription" placeholder="${project.description}" />
    </div>
    <div class="col-md-2">
        <form:label path="status" for="inputStatus" class="form-label">Status:
            <select name="status" class="form-control" style="margin-top: 8px; width: 200px">
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
        <form:label path="risk" for="inputRisk" class="form-label">Risk:
            <select name="risk" class="form-control" style="margin-top: 8px; width: 200px">
                <option value="LOW">Baixo risco</option>
                <option value="MEDIUM">Médio risco</option>
                <option value="HIGH">Alto risco</option>
            </select>
        </form:label>
    </div>
    <div class="col-md-2">
        <form:label path="budget" for="inputBudget" class="form-label">Budget</form:label>
        <form:input path="budget" type="text" class="form-control" id="inputBudget" placeholder="${project.budget}" />
    </div>
    <div class="col-md-2">
        <form:label path="startDate" for="inputStartDate" class="form-label">Start Date</form:label>
        <form:input path="startDate" type="date" class="form-control" id="inputStartDate" placeholder="${project.startDate}" />
    </div>
    <div class="col-md-2">
        <form:label path="expectedEndDate" for="inputExpectedEndDate" class="form-label">Expected End Date</form:label>
        <form:input path="expectedEndDate" type="date" class="form-control" id="inputExpectedEndDate" placeholder="${project.expectedEndDate}" />
    </div>
    <div class="col-md-2">
        <form:label path="endDate" for="inputEndDate" class="form-label">End Date</form:label>
        <form:input path="endDate" type="date" class="form-control" id="inputEndDate" placeholder="${project.endDate}" />
    </div>
    <div class="col-md-6">
        <form:label path="member" for="inputMember" class="form-label">Add Member
            <select name="member" class="form-control" style="margin-top: 10px; width: 450px">
                <option value="${null}"></option>
                <c:forEach items="${notMembers}" var="notMember">
                    <c:if test="${notMember.id != project.manager.id}">
                        <option value="${notMember.id}">${notMember.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </form:label>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end" style="margin-top: 10px">
        <button type="button" class="btn btn-secondary btn" onclick="window.history.go(-1);return false;">Cancel</button>
        <button type="submit" value="submit-edit" class="btn btn-primary btn">Save</button>
    </div>
</form:form>
</body>
</html>
