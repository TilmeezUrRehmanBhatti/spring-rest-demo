<%--
  Created by IntelliJ IDEA.
  User: tilme
  Date: 23/09/2022
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
Spring Rest Demo
<hr>
<a href="test/hello">Hello</a>
<hr>
<a href="${pageContext.request.contextPath}/api/students">Get All Students</a>
</body>
</html>
