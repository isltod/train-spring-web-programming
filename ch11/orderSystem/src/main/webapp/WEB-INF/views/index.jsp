<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>주문 관리 시스템</title>
  </head>
  <body>
    <%-- 이건 작동 안하고... --%>
    <%-- <a href="/logout">로그 아웃</a> --%>
    <form:form action="/logout" method="post">
      <input type="submit" value="로그 아웃">
    </form:form>
    <h1>주문 관리 시스템</h1>
    <p>
    <h2>회원 관리</h2>
      <form:form action="/enroll" method="get">
        <input type="submit" value="회원 등록">
      </form:form>
    <a href="/enroll">회원 등록</a><br>
    <a href="/enrolladmin">관리자 등록</a>
    </p>
    <p>
    <h2>고객 정보 관리</h2>
    <a href="/customer/edit">고객 정보 입력</a><br>
    <a href="/customer/list">고객 정보 조회</a><br>
    </p>
    <p>
    <h2>제품 정보 관리</h2>
    <a href="/product/edit">제품 정보 입력</a><br>
    <a href="/product/list">제품 정보 조회</a><br>
    <a href="/inventory/list">재고 정보 조회</a><br>
    </p>
    <p>
    <h2>주문 관리</h2>
    <a href="/order/create">제품 주문</a><br>
    <a href="/order/list">주문 조회</a><br>
    </p>
  </body>
</html>
