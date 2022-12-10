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
    <title>Title</title>
</head>
<body>
<jsp:include page="index.jsp" />
<form class="row m-12" style="width: 90%; margin-left: auto; margin-right: auto">
    <div class="col-md-6">
        <label for="inputProjectName" class="form-label">Project Name</label>
        <input type="text" class="form-control" id="inputProjectName" placeholder="${project.name}" disabled readonly>
    </div>
    <div class="col-md-6">
        <label for="inputManager" class="form-label">Manager</label>
        <input type="text" class="form-control" id="inputManager" placeholder="${project.manager.name}" disabled readonly>
    </div>
    <div class="col-12">
        <label for="inputDescription" class="form-label">Description</label>
        <input type="text" class="form-control" id="inputDescription" placeholder="${project.description}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputStatus" class="form-label">Status</label>
        <input type="text" class="form-control" id="inputStatus" placeholder="${project.status}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputRisk" class="form-label">Risk</label>
        <input type="text" class="form-control" id="inputRisk" placeholder="${project.risk}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputBudget" class="form-label">Budget</label>
        <input type="text" class="form-control" id="inputBudget" placeholder="${project.budget}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputStartDate" class="form-label">Start Date</label>
        <input type="text" class="form-control" id="inputStartDate" placeholder="${project.startDate}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputExpectedEndDate" class="form-label">Expected End Date</label>
        <input type="text" class="form-control" id="inputExpectedEndDate" placeholder="${project.expectedEndDate}" disabled readonly>
    </div>
    <div class="col-md-2">
        <label for="inputEndDate" class="form-label">End Date</label>
        <input type="text" class="form-control" id="inputEndDate" placeholder="${project.endDate}" disabled readonly>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end" style="margin-top: 10px">
        <button type="button" class="btn btn-secondary btn" data-bs-toggle="modal" data-bs-target="#modalDelete_${project.id}">Delete</button>
        <button type="button" class="btn btn-primary btn"><a href="/project/edit/${project.id}" style="text-decoration: none; color: #FFF">Edit</a></button>
    </div>
</form>

<div style="margin-right: auto; margin-left: auto; width: 60%; align-content: center">
    <table class="table table-striped caption-top table-responsive" style="width: 90%; margin-top: 10px; margin-left: auto; margin-right: auto">
        <caption>MEMBERS</caption>
        <thead>
        <tr>
            <th scope="col">Name</th>
<%--            <th scope="col">Action</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${members}" var="member">
            <tr>
                <td scope="row">${member.person.name}</td>
<%--                <td scope="row"><a href="/member/delete/${project.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="modalDelete_${project.id}" tabindex="-1" aria-labelledby="modalDeleteLabel" aria-hidden="true">
    <c:url var="del_project_url" value="/project/del/${project.id}"/>
    <form:form action="${del_project_url}" method="post" modelAttribute="project">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalDeleteLabel">Are you sure?</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this project?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="submit" value="submit" class="btn btn-primary">Yes</button>
            </div>
        </div>
    </div>
    </form:form>
</div>
</body>
</html>
