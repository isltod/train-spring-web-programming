<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Date 타입 변환</title>
  </head>
  <body>
    <h1>Date 타입 변환</h1>
    <form:form method="post" modelAttribute="dateDemo">
      <%-- DateDemo 객체의 date 속성에 날자 문자열을 넣어보는데... --%>
      <form:label path="date">Date: </form:label>
      <form:input path="date" type="text"/><br>
      <input type="submit" value="저장"/>
    </form:form>
  </body>
</html>
