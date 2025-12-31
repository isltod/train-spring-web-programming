<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>제품 입고</title>
    <link rel="stylesheet" href="/styles/main.css" type="text/css"/>
  </head>
  <body>
    <h1>제품 입고</h1>
    <form:form modelAttribute="inventory" method="post">
      <form:label path="id">ID: </form:label>
      <form:input type="text" path="id" readonly="true"/><br>
      <form:label path="name">제품명: </form:label>
      <form:input type="text" path="name" readonly="true"/><br>
      <form:label path="price">가격: </form:label>
      <form:input type="text" path="price" readonly="true"/><br>
      <form:label path="quantity">재고 수량: </form:label>
      <form:input type="text" path="quantity" readonly="true"/><br>
      <form:label path="stock">입고 수량: </form:label>
      <form:input type="text" path="stock" /><br>
      <input type="submit" value="저장">
    </form:form>
    <c:import url="/footer.jsp"/>
  </body>
</html>