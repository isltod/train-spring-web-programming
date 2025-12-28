<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>제품 정보 저장</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css"/>
  </head>
  <body>
    <h1>제품 정보 저장</h1>
    <form:form modelAttribute="product" method="post" enctype="multipart/form-data">
      <form:label path="id">ID: </form:label>
      <form:input type="text" path="id" readonly="true"/><br>
<%--      <form:label path="name">제품명: </form:label>--%>
<%--       국제화 --%>
      <form:label path="name"><spring:message code="product.name"/>: </form:label>
      <form:input type="text" path="name"/><br>
<%--      <form:label path="description">설명: </form:label>--%>
      <form:label path="description"><spring:message code="product.description"/>: </form:label>
      <form:input type="text" path="description"/><br>
<%--      <form:label path="price">가격: </form:label>--%>
      <form:label path="price"><spring:message code="product.price"/>: </form:label>
      <form:input type="text" path="price"/><br>
      <%-- 파일 업로드: type=file, 위 폼에 enctype=multipart --%>
      <input type="file" name="image"/><br>
      <input type="submit" value="저장">
    </form:form>
    <c:import url="/footer.jsp"/>
  </body>
</html>