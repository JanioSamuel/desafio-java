<%--
  Created by IntelliJ IDEA.
  User: samuel
  Date: 07/12/22
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Projects</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<table class="table table-striped" style="width: 90%; margin-top: 10px; margin-left: auto; margin-right: auto">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">status</th>
        <th scope="col">Manager</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${projects}" var="project">
        <tr>
            <td scope="row">${project.name}</td>
            <td scope="row">${project.status.value}</td>
            <td scope="row">${project.manager.name}</td>
            <td scope="row"><a href="/project/view-project/${project.id}">view</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
