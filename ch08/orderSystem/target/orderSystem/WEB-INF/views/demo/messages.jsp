<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>메시지 형식</title>
  </head>
  <body>
    <h1>메시지 형식</h1>
    <h2>MessageFormat 클래스</h2>
    ${s1}<br>
    ${s2}<br>
    ${s3}<br>
    ${s4}<br>
    ${s5}<br>
    ${s6}<br>
    ${s7}<br>
    <%-- MessageFormat에서 온건 괜찮은데, fmt는 \ 등이 깨지는데... --%>
    <h2>JSTL fmt 태그</h2>
    등록 건수: <fmt:formatNumber value="${count}" type="number"/><br>
    할인율: <fmt:formatNumber value="${discount}" type="percent"/><br>
    금액: <fmt:formatNumber value="${amount}" type="currency"/><br>
    총 주문액: <fmt:formatNumber value="${total}" pattern="#,##0"/> 원<br>
    주문 일자: <fmt:formatDate value="${date}" dateStyle="short"/><br>
    주문 시간: <fmt:formatDate value="${date}" timeStyle="long"/><br>
    배송 일자: <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/><br>
    배송 시간: <fmt:formatDate value="${date}" pattern="hh:mm"/><br>
  </body>
</html>
