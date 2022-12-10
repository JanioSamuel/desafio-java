<%--
  Created by IntelliJ IDEA.
  User: samuel
  Date: 08/12/22
  Time: 08:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="index.jsp" />
<c:if test="${message != null}">
    <div>${message}</div>
</c:if>
</body>
</html>
