<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Date 변환 결과</title>
  </head>
  <body>
    <h1>Date 변환 결과</h1>
    Date: <fmt:formatDate value="${dateDemo.date}" type="date"/><br>
  </body>
</html>
