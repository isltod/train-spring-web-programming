<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>고객 정보 저장</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css"/>
  </head>
  <body>
    <h1>고객 정보 저장</h1>
    <form:form modelAttribute="customer" method="post">
      <form:label path="id">ID: </form:label>
      <form:input type="text" path="id" readonly="true"/><br>
      <form:label path="name" cssErrorClass="valid-error-fail">이름: </form:label>
      <form:input type="text" path="name"/>
      <form:errors path="name" cssClass="valid-error-fail"/><br>
      <form:label path="address" cssErrorClass="valid-error-fail">주소: </form:label>
      <form:input type="text" path="address"/>
      <form:errors path="address" cssClass="valid-error-fail"/> <br>
      <form:label path="email" cssErrorClass="valid-error-fail">이메일: </form:label>
      <form:input type="text" path="email"/>
      <form:errors path="email" cssClass="valid-error-fail"/> <br>
      <input type="submit" value="저장">
    </form:form>
    <c:import url="/footer.jsp"/>
  </body>
</html>