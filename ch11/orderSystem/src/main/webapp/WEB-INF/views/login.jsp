<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link rel="stylesheet" href="/styles/main.css" type="text/css">
  </head>
  <body>
    <h1>로그인</h1>
    <form action="/login" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <label>ID: </label>
      <input type="text" name="username"><br>
      <label>비밀번호: </label>
      <input type="password" name="password"><br>
      <label>기억하기: </label>
      <input type="checkbox" name="remember-me"><br>
      <input type="submit" value="로그인">
    </form>
    <c:import url="/footer.jsp"/>
  </body>
</html>