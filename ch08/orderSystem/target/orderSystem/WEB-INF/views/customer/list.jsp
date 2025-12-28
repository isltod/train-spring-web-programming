<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
  <head>
    <title>고객 목록 조회</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css"/>
  </head>
  <body>
    <h1>고객 목록 조회</h1>
    <table>
      <tr>
        <th>이름</th>
        <th>주소</th>
        <th>메일</th>
      </tr>
      <c:forEach var="customer" items="${customers}">
        <tr>
          <td>${customer.name}</td>
          <td>${customer.address}</td>
          <td>${customer.email}</td>
          <td>
            <a href="/customer/update?id=${customer.id}">변경</a>
            <a href="/customer/delete?id=${customer.id}">삭제</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:import url="/footer.jsp"/>
  </body>
</html>