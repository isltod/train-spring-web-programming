<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원 등록</title>
    <link rel="stylesheet" href="/styles/main.css" type="text/css">
    <link rel="shortcut icon" href="#">
  </head>
  <body>
    <h1>회원 등록</h1>
    <form:form method="post" modelAttribute="member">
      <form:label path="name">사용자 ID: </form:label>
      <form:input path="name" type="text"/><br>
      <form:label path="password">비밀번호: </form:label>
      <form:input path="password" type="password"/><br>
      <input type="submit" value="등록">
    </form:form>
    <c:import url="/footer.jsp"/>
  </body>
</html>