<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mycompany.ordersystem.domain.Customer" %>
<html>
  <head>
    <title>고객 정보 삭제</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
  </head>
  <body>
    <h1>고객 정보 삭제</h1>
    <%
      Customer customer = (Customer) request.getAttribute("customer");
    %>
    <p>
      ID: ${customer.id}<br>
      고객명: ${customer.name}<br>
      주소: ${customer.address}<br>
      이메일: ${customer.email}<br>
    </p>
    <p>
      삭제하시겠습니까?
    </p>
    <form method="post">
      <input type="hidden" name="id" value="${customer.id}">
      <input type="submit" value="삭제">
    </form>
    <c:import url="/footer.jsp"/>
  </body>
</html>
