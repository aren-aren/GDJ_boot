<%--
  Created by IntelliJ IDEA.
  User: GD
  Date: 2024-03-08
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:message code="${result}" var="msg"></spring:message>
    <script>

        alert("${msg}");
        location.href="${path}";

    </script>
</body>
</html>
