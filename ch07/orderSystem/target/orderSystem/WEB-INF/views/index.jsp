<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>주문 관리 시스템</title>
  </head>
  <body>
    <h1>주문 관리 시스템</h1>
    <p>
      <h2>고객 정보 관리</h2>
      <a href="/customer/edit?lang=ko">고객 정보 입력</a><br>
      <a href="/customer/edit?lang=en">Customer Enrollment</a><br>
      <a href="/customer/list?lang=ko">고객 정보 조회</a><br>
    </p>
    <p>
      <h2>제품 정보 관리</h2>
      <a href="/product/edit?lang=ko">제품 정보 입력</a><br>
      <a href="/product/edit?lang=en">Product Enrollment</a><br>
      <a href="/product/list?lang=ko">제품 정보 조회</a><br>
      <a href="/product/list?lang=en">Product List</a><br>
      <a href="/inventory/list">재고 정보 조회</a><br>
    </p>
    <p>
      <h2>주문 관리</h2>
      <a href="/order/create">제품 주문</a><br>
      <a href="/order/list">주문 조회</a><br>
    </p>
    <p>
      <h2>데모</h2>
      <a href="/demo/date">Date 타입 변환</a><br>
      <a href="/demo/messages">메시지 형식</a><br>
    </p>
  </body>
</html>
