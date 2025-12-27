<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>제품 정보 등록</title>
  </head>
  <body>
    제품명: ${product.name} <br>
    제품 설명: ${product.description} <br>
    제품 가격: ${product.price}
    <c:import url="/footer.jsp"/>
  </body>
</html>
