<%--
  Created by IntelliJ IDEA.
  User: samuel
  Date: 08/12/22
  Time: 10:53
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
<c:url var="list_person_url" value="/person/list"/>
<form:form action="${list_person_url}" method="get" modelAttribute="employees">
<select name="manager">
<c:forEach items="${employees}" var="manager">
    <option value="${manager.id}">${manager.name}</option>
</c:forEach>
</select>
</form:form>
</body>
</html>
